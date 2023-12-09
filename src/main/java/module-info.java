module com.example.ap_project_endsem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ap_project_endsem to javafx.fxml;
    exports com.example.ap_project_endsem;
}