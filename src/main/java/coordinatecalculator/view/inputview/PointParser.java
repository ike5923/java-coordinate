package coordinatecalculator.view.inputview;

import coordinatecalculator.domain.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PointParser {
    private static final int POINT_X = 1;
    private static final int POINT_Y = 2;
    private static final String ERROR_NOT_INTEGER_POINT = "x 또는 y가 정수가 아닙니다.";
    private static final String ERROR_INPUT_PATTERN = "'-'로 구분하여 (x1,y1)-(x2, y2)... 형태로 입력하세요.";
    private static final String DELIMITER = "-";
    private static final String ERROR_EMPTY_INPUT = "입력이 없습니다.";

    private static Pattern pattern = Pattern.compile("\\((.*),(.*)\\)");

    public static List<Point> getValidPoints(String input) {
        checkEmptyInput(input);

        return fillValidPoints(input);
    }

    private static void checkEmptyInput(String input) {
        if (input == null || input.isEmpty()) {
            throw new NullPointerException(ERROR_EMPTY_INPUT);
        }
    }

    private static List<Point> fillValidPoints(String input) {
        List<Point> points = new ArrayList<>();

        String[] separatedPoints = input.split(DELIMITER);
        for (String separatedPoint : separatedPoints) {
            points.add(makePoint(separatedPoint));
        }

        return points;
    }

    private static Point makePoint(String separatedPoint) {
        Matcher matcher = pattern.matcher(separatedPoint);

        checkMatchPointPattern(matcher);

        return Point.of(getCoordinate(matcher, POINT_X), getCoordinate(matcher, POINT_Y));
    }

    private static void checkMatchPointPattern(Matcher matcher) {
        if (!matcher.find()) {
            throw new IllegalArgumentException(ERROR_INPUT_PATTERN);
        }
    }

    private static int getCoordinate(Matcher matcher, int coordinate) {
        return toInts(matcher.group(coordinate).trim());
    }

    private static int toInts(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException(ERROR_NOT_INTEGER_POINT);
        }
    }
}
