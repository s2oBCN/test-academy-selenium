package es.s2o.selenium.domain;

public enum Colors {
    RED {
        public String getColorCode() {
            return "#ff2600";
        }
    },
    BLUE {
        public String getColorCode() {
            return "#0433ff";
        }
    },
    GREEN {
        public String getColorCode() {
            return "#00f900";
        }
    },
    YELLOW {
        public String getColorCode() {
            return "#fffb00";
        }
    };

    public abstract String getColorCode();
}
