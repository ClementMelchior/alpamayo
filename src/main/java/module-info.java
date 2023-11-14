module melchior.clement {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires log4j;

    opens melchior.clement to javafx.fxml;
    exports melchior.clement;
}
