package com.example.cureya_chatbot.loadingAnim.factory;

import android.graphics.Path;
import android.graphics.Point;

import com.example.cureya_chatbot.loadingAnim.factory.path.Circle;
import com.example.cureya_chatbot.loadingAnim.factory.path.Diamond;
import com.example.cureya_chatbot.loadingAnim.factory.path.Infinite;
import com.example.cureya_chatbot.loadingAnim.factory.path.Square;
import com.example.cureya_chatbot.loadingAnim.factory.path.Star;
import com.example.cureya_chatbot.loadingAnim.factory.path.Triangle;


public class PathFactory {

  public static final String CIRCLE = "circle";
  public static final String SQUARE = "square";
  public static final String INFINITE = "infinite";
  public static final String STAR = "star";
  public static final String TRIANGLE = "triangle";
  public static final String DIAMOND = "diamond";

  public static Path makePath(String path, Point center, int pathWidth, int pathHeight,
      int maxBallSize) {

    switch (path) {
      case CIRCLE:
        return new Circle(center, pathWidth, pathHeight, maxBallSize).draw();
      case SQUARE:
        return new Square(center, pathWidth, pathHeight, maxBallSize).draw();
      case INFINITE:
        return new Infinite(center, pathWidth, pathHeight, maxBallSize).draw();
      case STAR:
        return new Star(center, pathWidth, pathHeight, maxBallSize).draw();
      case TRIANGLE:
        return new Triangle(center, pathWidth, pathHeight, maxBallSize).draw();
      case DIAMOND:
        return new Diamond(center, pathWidth, pathHeight, maxBallSize).draw();
      default:
        return new Infinite(center, pathWidth, pathHeight, maxBallSize).draw();
    }
  }
}

