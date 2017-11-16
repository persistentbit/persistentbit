package com.persistentbit.printable;



import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * TODO: Add comment
 *
 * @author Peter Muys
 * @since 9/01/2017
 */
public class PrintableList implements PrintableText{
    private final Function<Integer,String> bulletCreator;
    private final List<PrintableText>      items;

    private PrintableList(Function<Integer,String> bulletCreator, List<PrintableText> items) {
        this.bulletCreator = bulletCreator;
        this.items = items;
    }
    public PrintableList(){
        this(i -> "* ", List.of());
    }


    public PrintableList addItem(PrintableText item){
        List<PrintableText> newList = new ArrayList<>(items);
        newList.add(item);
        return new PrintableList(bulletCreator,newList);
    }

    @Override
	public void print(PrintTextWriter printWriter) {
		printWriter.print(PrintableText.indent("\t", true, out -> {
		    for(int t=0; t<items.size(); t++){
		        PrintableText item = items.get(t);
		        int index = t;
		        out.println(PrintableText.indent("  ",false, itemOut -> {
                    itemOut.print(bulletCreator.apply(index));
                    itemOut.println(item);
                    itemOut.flush();
                }));
            }

        }));
    }
}
