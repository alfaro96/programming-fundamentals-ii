# Geometry

This task focuses on class collaboration. You will build a coordinate system where a `Vector` object uses `Point` objects to define its position and behavior in two-dimensional space.

---

## Objective

The goal is to define two classes, `Point` and `Vector`, to represent two-dimensional Cartesian coordinates and directed lines, implementing geometric operations like distance, colinearity, and centroids.

---

## Requirements

### `Point`

* **Attributes**: `double x` and `double y`.
* **Constructors**: A default one at $ (0, 0) $ and a standard one for specific coordinates.
* **Methods**:
  * `distance(Point other)`: Returns Euclidean distance.
  * `isColinearTo(Point p1, Point p2)`: Checks if three points belong to one line.
  * `middlePoint(Point other)`: Returns a new `Point` exactly between the two.
  * `toString()` and `equals()`.

### `Vector`

* **Attributes**: `Point originPoint` and `Point endPoint`.
* **Constructor**: Takes origin and end points as arguments.
* **Methods**:
  * `add(Vector other)` and `subtract(Vector other)`: Returns a new `Vector` resulting from the operation.
  * `scalarProduct(Vector other)`: Returns a new `Vector` representing the product.
  * `centroid(Vector v2, Vector v3)`: Returns a `Point` representing the barycenter of three vectors.
  * `toString()` and `equals()`.

---

## Evaluation criteria

To successfully complete the exercise, implement internal `main` methods in both classes to:

1. **Point testing**: Define four points, compute distances, check colinearity, and find middle points.
2. **Vector testing**: Define five vectors, perform additions and subtractions, and calculate centroids.
3. **Clean code**: Ensure professional Javadoc and legible logic.

---

## View the files

* [Point.java](file://src/Point.java)
* [Vector.java](file://src/Vector.java)
