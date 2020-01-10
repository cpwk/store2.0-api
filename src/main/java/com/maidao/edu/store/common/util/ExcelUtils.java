package com.maidao.edu.store.common.util;

import com.maidao.edu.store.common.exception.ServiceException;
import com.sunnysuperman.commons.util.FileUtil;
import com.sunnysuperman.commons.util.FormatUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

import static com.maidao.edu.store.common.exception.ErrorCode.ERROR_UNFIND_FILE;

public class ExcelUtils {

    public static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellTypeEnum()) {
            case STRING: {
                return StringUtils.trimToNull(cell.getStringCellValue());
            }
            case NUMERIC: {
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    return FormatUtil.formatISO8601Date(date);
                }
                double d = cell.getNumericCellValue();
                long l = (long) d;
                if (l == d) {
                    return String.valueOf(l);
                } else {
                    return String.valueOf(d);
                }
            }
            case BLANK:
                return null;
            default:
                throw new RuntimeException("Failed to find string cell value: " + cell.getCellTypeEnum());
        }
    }

    public static Number getNumericCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                return FormatUtil.parseNumber(StringUtils.trimToNull(cell.getStringCellValue()));
            case BLANK:
                return null;
            default:
                throw new RuntimeException("Failed to find numeric cell value: " + cell.getCellTypeEnum());
        }
    }

    public static int getIntCellValue(Cell cell) {
        Number number = getNumericCellValue(cell);
        return number == null ? 0 : number.intValue();
    }

    public static float getFloatCellValue(Cell cell) {
        Number number = getNumericCellValue(cell);
        return number == null ? 0f : number.floatValue();
    }

    public static double getDoubleCellValue(Cell cell) {
        Number number = getNumericCellValue(cell);
        return number == null ? 0d : number.doubleValue();
    }

    public static long getLongCellValue(Cell cell) {
        Number number = getNumericCellValue(cell);
        return number == null ? 0L : number.longValue();
    }

    private static int findCellIndex(Row row, String cellName) throws ExcelException {
        int firstCell = row.getFirstCellNum();
        int lastCell = row.getLastCellNum();
        for (int i = firstCell; i <= lastCell; i++) {
            Cell cell = row.getCell(i);
            if (cell == null) {
                continue;
            }
            String title = StringUtils.trimToNull(getStringCellValue(cell));
            if (title.equals(cellName)) {
                return i;
            }
        }
        throw new ExcelException(ExcelException.ERROR_COULD_NOT_FIND_COLUMN, cellName);
    }

    public static HSSFWorkbook get2003Workbook(InputStream in) throws ExcelException {
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(in);
        } catch (IOException e) {
            throw new ExcelException(ExcelException.ERROR_NOT_AN_EXCEL_FILE);
        } finally {
            FileUtil.close(in);
        }
        return wb;
    }

    public static XSSFWorkbook get2007Workbook(InputStream in) throws ExcelException {
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook(in);
        } catch (IOException e) {
            throw new ExcelException(ExcelException.ERROR_NOT_AN_EXCEL_FILE);
        } finally {
            FileUtil.close(in);
        }
        return wb;
    }

    public static Workbook getWorkbook(File file) throws ExcelException {
        InputStream in;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new ExcelException(ExcelException.ERROR_NOT_AN_EXCEL_FILE);
        }
        return getWorkbook(in, file.getName());
    }

    public static Workbook getWorkbook(InputStream in, String fileName) throws ExcelException {
        String ext = FileUtil.getFileExt(fileName);
        if (ext != null) {
            if (ext.equals("xlsx")) {
                return get2007Workbook(in);
            } else if (ext.equals("xls")) {
                return get2003Workbook(in);
            }
        }
        try {
            return get2007Workbook(in);
        } catch (ExcelException e) {
            if (e.getErrorCode() == ExcelException.ERROR_NOT_AN_EXCEL_FILE) {
                return get2003Workbook(in);
            }
            throw e;
        }
    }

    public static List<String> readHeader(Sheet sheet) {
        Row row = sheet.getRow(0);
        int firstCell = row.getFirstCellNum();
        int lastCell = row.getLastCellNum();
        List<String> keys = new ArrayList<String>(lastCell - firstCell + 1);
        for (int i = firstCell; i <= lastCell; i++) {
            Cell cell = row.getCell(i);
            if (cell != null) {
                keys.add(getStringCellValue(cell));
            }
        }
        return keys;
    }

    public static <M> List<M> read(File file, ExcelColumn[] columns, RowParser<M> parser)
            throws ExcelException, Exception {
        Workbook wb = getWorkbook(file);
        Sheet sheet = wb.getSheetAt(0);
        return read(sheet, columns, parser);
    }

    public static <M> List<M> read(Sheet sheet, ExcelColumn[] columns, RowParser<M> parser) throws Exception {
        int firstRow = sheet.getFirstRowNum();
        int lastRow = sheet.getLastRowNum();
        if (firstRow != 0) {
            throw new ExcelException(ExcelException.ERROR_DATA_IS_EMPTY);
        }
        Row titleRow = sheet.getRow(firstRow);
        int[] indexes = new int[columns.length];
        for (int i = 0; i < columns.length; i++) {
            indexes[i] = findCellIndex(titleRow, columns[i].getTitle());
        }

        List<M> list = new ArrayList<M>();
        for (int i = firstRow + 1; i <= lastRow; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            Map<String, Object> item = new HashMap<String, Object>();
            for (int k = 0; k < indexes.length; k++) {
                Cell cell = row.getCell(indexes[k]);
                if (cell == null) {
                    continue;
                }
                ExcelColumn column = columns[k];
                Object value = null;
                switch (column.getType()) {
                    case STRING:
                        value = getStringCellValue(cell);
                        break;
                    case INT:
                        value = getIntCellValue(cell);
                        break;
                    case FLOAT:
                        value = getFloatCellValue(cell);
                        break;
                    case DOUBLE:
                        value = getDoubleCellValue(cell);
                        break;
                    case LONG:
                        value = getLongCellValue(cell);
                        break;
                    default:
                        throw new ExcelException(ExcelException.ERROR_UNKNOWN_CELL_TYPE, column.getType());
                }
                item.put(column.getKey(), value);
            }
            M object = parser.parse(item, list, i);
            if (object == null) {
                continue;
            }
            list.add(object);
        }
        return list;
    }

    public static <D> void export(Sheet sheet, String[] titles, Iterable<D> iter, RowRenderer<D> renderer) {
        int rowIndex = 0;

        Row titleRow = sheet.createRow(rowIndex);
        for (int i = 0; i < titles.length; i++) {
            titleRow.createCell(i).setCellValue(titles[i]);
        }

        for (D item : iter) {
            Row row = sheet.createRow(++rowIndex);
            renderer.render(row, item);
        }
    }

    public static Date getDateCellValue(String s, String key) throws Exception {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        StringBuilder buf = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // skip bad characters
            if (c > 0 && c < 128) {
                buf.append(c);
            }
        }
        s = buf.toString();
        Date d = null;
        try {
            d = DateUtils.parseDate(s);
        } catch (Exception e) {
            // ignore
        }
        if (d == null) {
            throw new ServiceException(ERROR_UNFIND_FILE);
        }
        return d;
    }

    public static enum ExcelCellType {
        STRING(1), INT(2), FLOAT(3), DOUBLE(4), LONG(5);

        private byte value;

        private ExcelCellType(int value) {
            this.value = (byte) value;
        }

        public static ExcelCellType find(byte value) {
            for (ExcelCellType item : ExcelCellType.values()) {
                if (item.value == value) {
                    return item;
                }
            }
            return null;
        }

        public byte value() {
            return value;
        }
    }

    public static interface RowRenderer<R> {
        void render(Row row, R item);
    }

    public static interface RowParser<P> {
        P parse(Map<String, Object> dataAsMap, List<P> list, int rowIndex) throws Exception;
    }

    public static class ExcelColumn {
        private String key;
        private String title;
        private ExcelCellType type;

        public ExcelColumn(String key, String title, ExcelCellType type) {
            super();
            this.key = key;
            this.title = title == null ? key : title;
            this.type = type;
        }

        public ExcelColumn(String key, String title) {
            this(key, title, ExcelCellType.STRING);
        }

        public String getKey() {
            return key;
        }

        public String getTitle() {
            return title;
        }

        public ExcelCellType getType() {
            return type;
        }

    }

    public static class DefaultRowParser implements RowParser<Map<String, Object>> {

        @Override
        public Map<String, Object> parse(Map<String, Object> dataAsMap, List<Map<String, Object>> list, int rowIndex)
                throws Exception {
            return dataAsMap;
        }
    }

    public static class ExcelException extends Exception {
        public static final int ERROR_NOT_AN_EXCEL_FILE = 1;
        public static final int ERROR_DATA_IS_EMPTY = 2;
        public static final int ERROR_COULD_NOT_FIND_COLUMN = 3;
        public static final int ERROR_UNKNOWN_CELL_TYPE = 4;
        private static final long serialVersionUID = 7945107291355792219L;
        private int errorCode;
        private Object[] errorParams;

        public ExcelException(int errorCode) {
            super();
            this.errorCode = errorCode;
        }

        public ExcelException(int errorCode, Object... errorParams) {
            super();
            this.errorCode = errorCode;
            this.errorParams = errorParams;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public Object[] getErrorParams() {
            return errorParams;
        }

    }
}
