module com.example.textanalyzerfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.wolfe.textanalyzerfx to javafx.fxml;
    exports com.wolfe.textanalyzerfx;
}