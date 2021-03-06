package com.openblocks.blocks.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import androidx.annotation.NonNull;

/**
 * A simple helper class used to draw stuff on the canvas
 */
public class DrawHelper {

    // There are basically no way except this
    static Paint paint;

    public static void initialize() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public static void drawBooleanField(Canvas canvas, int x, int y, int width, int height, int color) {
        paint.setColor(color);

        drawBooleanField(canvas, x, y, width, height, paint);
    }

    public static void drawBooleanField(Canvas canvas, int x, int y, int width, int height, Paint p) {
        int half_height = height / 2;
        int middle = y + half_height;

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);

        ////////////////////////////////////////////////////////////////////////////////

        // Draw the left triangle

        // Start from the bottom part of the triangle
        path.moveTo(x + half_height, y + height);

        // Then go to the sharp point (left) of the triangle
        path.lineTo(x, middle);

        // Then go to the y part of the left triangle
        path.lineTo(x + half_height, y);

        ////////////////////////////////////////////////////////////////////////////////

        // Draw the right triangle

        // Move to the right triangle's top part
        path.lineTo(x + width - half_height, y);

        // Then go to the sharp point (right) of the triangle
        path.lineTo(x + width, middle);

        // Then go to the bottom part of the right triangle
        path.lineTo(x + width - half_height, y + height);

        ////////////////////////////////////////////////////////////////////////////////

        // Finally, go back to the previous bottom part of the x triangle to connect the lines
        path.moveTo(x + half_height, y + height);
        path.close();

        canvas.drawPath(path, p);
    }


    public static void drawIntegerField(Canvas canvas, int x, int y, int width, int height, int color) {
        paint.setColor(color);

        drawIntegerField(canvas, x, y, width, height, paint);
    }

    public static void drawIntegerField(@NonNull Canvas canvas, int x, int y, int width, int height, Paint p) {
        int half_height = height / 2;
        int middle = half_height + y;

        // Draw the oval-ly background
        // Draw the x circle
        canvas.drawCircle(half_height + x, middle, half_height, p);

        // Draw the right circle
        canvas.drawCircle(x + width - half_height, middle, half_height, p);

        // Draw a rectangle between the half part of the x circle to the half part of the right circle
        canvas.drawRect(x + half_height, y, x + width - half_height, y + height, p);
    }


    // The shadow will be drawn inside the block
    public static void drawRectSimpleInsideShadow(@NonNull Canvas canvas, int x, int y, int width, int height, int shadow_height, int color) {
        drawRectSimpleOutsideShadow(canvas, x, y, width, height - shadow_height, shadow_height, color);
    }

    // The shadow will be inside the block, not outside
    public static void drawRoundRectSimpleOutsideShadow(@NonNull Canvas canvas, int x, int y, int width, int height, int shadow_height, int radius, int color) {
        // Draw shadow
        drawRoundRect(canvas, x, y, width, height, radius, manipulateColor(color, 0.8f));

        // Draw the actual block
        drawRoundRect(canvas, x, y, width, height - shadow_height, radius, color);
    }

    // The shadow will be inside the block, not outside
    public static void drawRectSimpleOutsideShadow(@NonNull Canvas canvas, int x, int y, int width, int height, int shadow_height, int color) {
        // Draw shadow
        drawRect(canvas, x, y, width, height, manipulateColor(color, 0.8f));

        // Draw the actual block
        drawRect(canvas, x, y, width, height - shadow_height, color);
    }

    public static void drawRectAbsolute(@NonNull Canvas canvas, int x, int y, int x1, int y1, int color) {
        paint.setColor(color);

        canvas.drawRect(x, y, x1, y1, paint);
    }

    public static void drawRect(@NonNull Canvas canvas, int x, int y, int width, int height, int color) {
        paint.setColor(color);

        canvas.drawRect(x, y, x + width, y + height, paint);
    }

    public static void drawRoundRect(@NonNull Canvas canvas, int x, int y, int width, int height, int radius, int color) {
        paint.setColor(color);

        canvas.drawRoundRect(x, y, x + width, y + height, radius, radius, paint);
    }

    /**
     * Multiples color by the factor (should be below 1.0f)
     *
     * @param color The color
     * @param factor The factor (should be below 1.0f)
     * @return The multiplied color
     */
    public static int manipulateColor(int color, float factor) {
        int a = Color.alpha(color);

        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);

        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }
}
