module com.example.tetris {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.tetris to javafx.fxml;
    exports com.example.tetris;
    exports com.example.tetris.figure;
    opens com.example.tetris.figure to javafx.fxml;
}