import wagu.*;
import java.util.ArrayList;
import java.util.List;

public class MyTable {

    public static void printTable(Rules a){
        String header = "PC \\ User";
        List<String> headerList = new ArrayList<>();
        headerList.add(header);
        headerList.addAll(a.obj);
        List<List<String>> rowsList = new ArrayList<>();
        generateRows(rowsList, a);
        List<Integer> colWidthsListEdited = new ArrayList<>();
        int tableWidth = generateColEditor(colWidthsListEdited, a);
        Board board = new Board(tableWidth);
        Table table = new Table(board,tableWidth, headerList, rowsList);
        table.setGridMode(Table.GRID_FULL).setColWidthsList(colWidthsListEdited);
        Block tableBlock = table.tableToBlocks();
        board.setInitialBlock(tableBlock);
        board.build();
        String tableString = board.getPreview();
        System.out.println(tableString);
    }

    private static int generateColEditor(List<Integer> colWidthsListEdited, Rules a) {
        int minWidth = 10;
        int maxWidth = 10;
        int tableWidth = 0;
        for(int i = 0; i < a.obj.size(); i++){
            int tempWidth = a.obj.get(i).length() + 2;
            if(tempWidth-1 > maxWidth){
                maxWidth = tempWidth - 1;
            }
            if(tempWidth > minWidth){
                colWidthsListEdited.add(tempWidth);
                tableWidth += tempWidth;
            }
            else {
                colWidthsListEdited.add(minWidth);
                tableWidth += minWidth;
            }
        }
        colWidthsListEdited.add(0, maxWidth);
        tableWidth += maxWidth;
        tableWidth += a.obj.size() + 2;
        return tableWidth;
    }

    private static void generateRows(List<List<String>> rowsList, Rules a) {
        for(int i = 0; i < a.obj.size(); i++){
            List<String> row = new ArrayList<>();
            row.add(a.obj.get(i) + " ");
            for(int j = 0; j < a.obj.size(); j++) {
                row.add(" " + a.isWinner(j, i) + " ");
            }
            rowsList.add(row);
        }
    }
}
