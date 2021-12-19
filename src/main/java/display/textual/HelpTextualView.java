package display.textual;

import display.View;

public class HelpTextualView extends View {
    @Override
    public String typeOfView() {
        return "HelpTextual";
    }

    @Override
    public void print() {
        HostPage.print();
        HelpPage.print();
    }

    @Override
    public void printErrorMessage(String title, String message) {
        System.out.println("");
    }
}
