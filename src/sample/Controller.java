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

    public void drawJuliasFractal(ActionEvent actionEvent) {
        clear(actionEvent);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLACK);
        double mx = canvas.getWidth();
        double my = canvas.getHeight() - 100;
        double cRe, cIm;
        double newRe, newIm, oldRe, oldIm;
        double zoom = 1, moveX = 0, moveY = 0;
        int maxIterations = 300;
        cRe = -0.70176;
        cIm = -0.3842;

        for (int x = 0; x < mx; x++)
            for (int y = 0; y < my; y++) {
                newRe = 1.5 * (x - mx / 2) / (0.5 * zoom * mx) + moveX;
                newIm = (y - my / 2) / (0.5 * zoom * my) + moveY;

                int i;

                for (i = 0; i < maxIterations; i++) {
                    oldRe = newRe;
                    oldIm = newIm;

                    newRe = oldRe * oldRe - oldIm * oldIm + cRe;
                    newIm = 2 * oldRe * oldIm + cIm;

                    if ((newRe * newRe + newIm * newIm) > 4) break;
                }

                graphicsContext.setFill(Color.rgb(255, (i * 9) % 255, 0, ((double)i * 9) % 255 / 255));
                fillPixel(x, y);
            }
    }

    private void fillPixel(double x, double y) {
        canvas.getGraphicsContext2D().fillRect(x, y, 1, 1);
    }
}
