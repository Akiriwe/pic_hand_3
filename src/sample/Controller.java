package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class Controller {
  @FXML
  private TextField sphereRadiusPx;
  @FXML
  private Canvas canvas;

  public void drawSphere(ActionEvent actionEvent) {
    clear(actionEvent);

    Double radius = Double.parseDouble(sphereRadiusPx.getText());
    double radiusSquare = radius * radius;
    double xCenter = canvas.getWidth() / 2;
    double yCenter = canvas.getHeight() / 2;
    GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    for (double x = 0; x <= radiusSquare; ++x) {
      for (double y = 0; y <= x; ++y) {
        double squaredDistanceToCenter = x * x + y * y;

        if (squaredDistanceToCenter > radiusSquare) {
          break;
        }

        double reflectivity = squaredDistanceToCenter / radiusSquare;
        graphicsContext.setFill(Color.rgb(235, 134, 134, reflectivity));

        fillPixel(xCenter + x, yCenter + y);
        fillPixel(xCenter + x, yCenter - y);
        fillPixel(xCenter - x, yCenter + y);
        fillPixel(xCenter - x, yCenter - y);
        fillPixel(xCenter + y, yCenter + x);
        fillPixel(xCenter + y, yCenter - x);
        fillPixel(xCenter - y, yCenter + x);
        fillPixel(xCenter - y, yCenter - x);
      }
    }
  }

  

  public void clear(ActionEvent actionEvent) {
    GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
  }

  private void fillPixel(double x, double y) {
    canvas.getGraphicsContext2D().fillRect(x, y, 1, 1);
  }

  public void drawMandelbrotFractal(ActionEvent actionEvent) {

  }
}
