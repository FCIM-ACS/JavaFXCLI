module com.example.clientserverfx {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.clientserverfx to javafx.fxml;
    exports com.example.clientserverfx;
}