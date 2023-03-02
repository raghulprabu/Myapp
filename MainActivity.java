public class SpreadsheetController {

    @GetMapping("/spreadsheet")
    public ModelAndView displaySpreadsheetData() throws IOException {
        // Load the spreadsheet file
        FileInputStream file = new FileInputStream(new File("path/to/spreadsheet.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        // Get the first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Create a list to hold the rows of data
        List<RowData> rowDataList = new ArrayList<>();

        // Iterate through the rows of data and add them to the list
        for (Row row : sheet) {
            RowData rowData = new RowData();
            rowData.setColumn1(row.getCell(0).getStringCellValue());
            rowData.setColumn2(row.getCell(1).getStringCellValue());
            rowData.setColumn3(row.getCell(2).getStringCellValue());
            rowDataList.add(rowData);
        }

        // Close the workbook and file input stream
        workbook.close();
        file.close();

        // Create a model and view object and add the row data to it
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("spreadsheet");
        modelAndView.addObject("rowDataList", rowDataList);

        return modelAndView;
    }

}