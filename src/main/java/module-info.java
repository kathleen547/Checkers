module com.example.checkersjavafx {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.checkersjavafx to javafx.fxml;
    exports com.example.checkersjavafx;
}