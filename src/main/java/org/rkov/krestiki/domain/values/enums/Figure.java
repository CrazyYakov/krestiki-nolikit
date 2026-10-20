package org.rkov.krestiki.domain.values.enums;


public enum Figure {
    TIK, TAK;
    

    public String label() {
        switch (this) {
            case TIK -> {
                return "Крестик";
            }
            case TAK -> {
                return "Нолик";
            }
        }

        return this.name();
    }

    public String shortLabel() {
        switch (this) {
            case TIK -> {
                return "X";
            }
            case TAK -> {
                return "O";
            }
        }

        return this.name();
    }
}
