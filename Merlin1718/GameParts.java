package org.firstinspires.ftc.teamcode.team.Merlin1718;



public class GameParts {

    private class Glyph {
        boolean gray;
        boolean brown;
        Glyph ( boolean gray) {
            this.gray = gray;
            this.brown = !gray;
        }
    }
    private class Ciphers {
        private Glyph gray = new Glyph(true);
        private Glyph brown = new Glyph(false);
        private Glyph[][] frog1 = {
                {brown, gray, brown, gray},
                {gray, brown, gray, brown},
                {brown, gray, brown, gray}
        };
        private Glyph[][] frog2 = {
                {gray, brown, gray, brown},
                {brown, gray, brown, gray},
                {gray, brown, gray, brown}
        };
        private Glyph[][] bird1 = {
                {gray, brown, brown, gray},
                {brown, gray, gray, brown},
                {gray, brown, brown, gray}
        };
        Glyph[][] bird2 = {
                {brown, gray, gray, brown},
                {gray, brown, brown, gray},
                {brown, gray, gray, brown}
        };
        Glyph[][] snake1 = {
                {gray, gray, brown, brown},
                {gray, brown, brown, gray},
                {brown, brown, gray, gray}
        };
        Glyph[][] snake2 = {
                {brown, brown, gray, gray},
                {brown, gray, gray, brown},
                {gray, gray, brown, brown}
        };

        Glyph[][][] cyphers = {frog1, frog2, bird1, bird2, snake1, snake2};

        public boolean cypherPossible (Glyph[][] box, int i) {
            for(int col = 0; col < 3; col++) {
                for(int row = 0; row < 4; row++) {
                    if(box[col][row] == null) break;
                    if(box[col][row] != this.cyphers[i][col][row]) return false;
                }
            }
            return true;
        }
    }
    public class Cryptobox {
        Glyph[][] box = {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
        };

        Ciphers ciphers = new Ciphers();
        boolean [] cypherPossible = {true, true, true, true, true, true};

        public boolean place (int column, boolean gray){
            for(int i = 0; i < 4; i++){
                if(box[column][i] == null) {
                    box[column][i] = new Glyph(gray);
                    setCypherPossible();
                    return true;
                }
            }
            return false;
        }
        public void setCypherPossible () {
            for(int i = 0; i < this.ciphers.cyphers.length; i++){
                this.cypherPossible[i] = ciphers.cypherPossible(this.box, i);
            }
        }
        public Possibility[] singlePossibles(){
            Possibility[] possibilities = {null};
            for(int i = 0; i < this.ciphers.cyphers.length; i++) {
                if(cypherPossible[i]){
                    for(int col = 0; col < 3; col++){
                        for(int row = 0; row < 4; row++){
                            if(box[col][row] == null){
                                possibilities[possibilities.length].glyph = this.ciphers.cyphers[i][col][row];
                                possibilities[possibilities.length].column = col;
                            }
                        }
                    }
                }
            }
            return possibilities;
        }
        public DoubleLayerPossibility[] doublePossibles () {
            DoubleLayerPossibility[] possibilities = {null};
            for(int i = 0; i < this.ciphers.cyphers.length; i++) {
                if(cypherPossible[i]){
                    for(int col = 0; col < 3; col++){
                        for(int row = 0; row < 3; row++){
                            if(box[col][row] == null){
                                Possibility zero = new Possibility(this.ciphers.cyphers[i][col][row].gray, col);
                                Possibility one = new Possibility(this.ciphers.cyphers[i][col][row+1].gray, col);
                            }
                        }
                    }
                }
            }
            return possibilities;
        }
    }
    public class Possibility {
        Glyph glyph;
        int column;
        Possibility (boolean gray, int column){
            this.glyph = new Glyph(gray);
            this.column = column;
        }
    }
    public class DoubleLayerPossibility {
        Possibility zero;
        Possibility one;
        DoubleLayerPossibility (Possibility zero, Possibility one) {
            this.zero = zero;
            this.one = one;
        }
    }

}
