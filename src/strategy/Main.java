package src.strategy;

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        String text = "hello world from java";

        editor.setFormatter(new UpperCaseFormatter());
        editor.publishText(text);

        editor.setFormatter(new LowerCaseFormatter());
        editor.publishText(text);

        editor.setFormatter(new CapitalizeFormatter());
        editor.publishText(text);
    }
}