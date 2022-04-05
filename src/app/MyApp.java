package app;

import errors.ErrorFactory;

public class MyApp {

    public static void main(String[] args) {

        ErrorFactory errorFactory = ErrorFactory.getInstance();
        MainFrame mainFrame = MainFrame.getInstance();
        errorFactory.addSubscriber(mainFrame);

        mainFrame.setVisible(true);
    }

}
