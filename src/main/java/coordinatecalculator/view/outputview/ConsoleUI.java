package coordinatecalculator.view.outputview;

import coordinatecalculator.domain.Point;
import coordinatecalculator.domain.PointGroup;

class ConsoleUI {
    private static final int LIMIT_MAX_COORDINATE = 24;
    private static final int LIMIT_MIN_COORDINATE = 0;
    private static final String NEXT_LINE = "\n";
    private static final String DOUBLE_SPACE = "  ";
    private static final String ALIGN_RIGHT_NUM = "%2d";
    private static final String ALIGN_RIGHT_POINT = "%2s";
    private static final String POINT = "•";
    private static final String Y_BAR = "|";
    private static final int ZERO = 0;
    private static final String ORIGIN_ZERO = " 0 ";
    private static final int CONVERT_EVEN = 2;
    private static final String ORIGIN_CHARACTER = "+";
    private static final String X_BAR = "--";
    private static final String X_BAR_POINT = "-•";
    private static StringBuilder stringBuilder;

    static void printConsoleUI(PointGroup pointGroup) {
        drawConsoleUI(pointGroup);
        System.out.println(stringBuilder.toString());
    }

    private static void drawConsoleUI(PointGroup pointGroup) {
        stringBuilder = new StringBuilder();
        moveY(pointGroup);
    }

    private static void moveY(PointGroup pointGroup) {
        for (int yCoordinate = LIMIT_MAX_COORDINATE; yCoordinate > LIMIT_MIN_COORDINATE; yCoordinate--) {
            stringBuilder.append(drawAxisY(yCoordinate));
            moveX(yCoordinate, pointGroup);
            stringBuilder.append(NEXT_LINE);
        }
        moveAxisX(pointGroup);
    }

    private static String drawAxisY(int lineNum) {
        if (isEvenNum(lineNum)) {
            return String.format(ALIGN_RIGHT_NUM, lineNum);
        }

        return DOUBLE_SPACE;
    }

    private static boolean isEvenNum(int number) {
        return number % CONVERT_EVEN == ZERO;
    }

    private static void moveX(int yCoordinate, PointGroup pointGroup) {
        for (int xCoordinate = LIMIT_MIN_COORDINATE; xCoordinate <= LIMIT_MAX_COORDINATE; xCoordinate++) {
            stringBuilder.append(getDrawnPoint(xCoordinate, yCoordinate, pointGroup));
        }
    }

    private static String getDrawnPoint(final int xCoordinate, final int yCoordinate, PointGroup pointGroup) {
        if (xCoordinate == ZERO) {
            return drawPointAtXZero(yCoordinate, pointGroup);
        }

        return drawPoint(xCoordinate, yCoordinate, pointGroup);
    }

    private static String drawPointAtXZero(final int yCoordinate, PointGroup pointGroup) {
        if (pointGroup.isContainsPoint(Point.of(ZERO, yCoordinate))) {
            return POINT;
        }

        return Y_BAR;
    }

    private static String drawPoint(int xCoordinate, int yCoordinate, PointGroup pointGroup) {
        if (pointGroup.isContainsPoint(Point.of(xCoordinate, yCoordinate))) {
            return String.format(ALIGN_RIGHT_POINT, POINT);
        }

        return DOUBLE_SPACE;
    }

    private static void moveAxisX(PointGroup pointGroup) {
        for (int xCoordinate = LIMIT_MIN_COORDINATE; xCoordinate <= LIMIT_MAX_COORDINATE; xCoordinate++) {
            stringBuilder.append(drawAxisX(xCoordinate, pointGroup));
        }
        stringBuilder.append(NEXT_LINE);
        moveAxisXNum();
    }

    private static String drawOrigin(PointGroup pointGroup) {
        if (pointGroup.isContainsPoint(Point.of(ZERO, ZERO))) {
            return POINT;
        }

        return ORIGIN_CHARACTER;
    }

    private static String drawAxisX(final int xCoordinate, PointGroup pointGroup) {
        if (xCoordinate == ZERO) {
            return drawAtXZero(pointGroup);
        }

        if (pointGroup.isContainsPoint(Point.of(xCoordinate, ZERO))) {
            return X_BAR_POINT;
        }

        return X_BAR;
    }

    private static String drawAtXZero(PointGroup pointGroup) {
        return DOUBLE_SPACE + drawOrigin(pointGroup);
    }

    private static void moveAxisXNum() {
        for (int xCoordinate = LIMIT_MIN_COORDINATE; xCoordinate <= LIMIT_MAX_COORDINATE; xCoordinate++) {
            stringBuilder.append(drawAxisXNum(xCoordinate));
        }
        stringBuilder.append(NEXT_LINE);
    }

    private static String drawAxisXNum(final int xCoordinate) {
        if (xCoordinate == ZERO) {
            return ORIGIN_ZERO;
        }

        if (isEvenNum(xCoordinate)) {
            return String.format(ALIGN_RIGHT_NUM, xCoordinate);
        }

        return DOUBLE_SPACE;
    }
}
