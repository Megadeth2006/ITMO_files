package Model;

import Validator.Validating;

abstract public class Element implements Comparable<Element>, Validating {
    abstract public int getId();
}
