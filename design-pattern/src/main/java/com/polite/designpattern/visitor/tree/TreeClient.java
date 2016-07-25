package com.polite.designpattern.visitor.tree;

/**
 * A client with visitors
 * @author polite
 * @date 2016-07-25 .
 */
public class TreeClient {
    public static <T> String toString(Tree<T> tree){
      return   tree.visit(new Visitor<T, String>() {
            public String leaf(T t) {
                return t.toString();
            }

            public String branch(String left, String right) {
                return "("+left+"^"+right+")";
            }
        });
    }

    public static <T extends Number> double sum(Tree<T> tree){
      return   tree.visit(new Visitor<T, Double>() {
          public Double leaf(T t) {
              return t.doubleValue();
          }

          public Double branch(Double left, Double right) {
              return left + right;
          }
      });
    }

    public static void main(String[] args) {
        Tree<Integer> integerTree = Tree.branch(
                Tree.branch(
                        Tree.leaf(1),
                        Tree.leaf(2)),
                Tree.leaf(3));

        String s = toString(integerTree);
        double sum = sum(integerTree);
        assert "((1^2)^3)".equals(s);
        assert sum == 6;

    }
}
