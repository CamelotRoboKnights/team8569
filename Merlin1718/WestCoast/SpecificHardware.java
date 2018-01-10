/**
 * This class contains the objects that are specific to this years game
 * It contains a Jewel Sorter and a Glyph Collector
 *
 * The Jewel Sorter contains a color sensor and a servo. It can raise and lower the servo as well as
 *      determine the jewel color by determining if there is more red or blue being sensed
 *
 * The Glyph Collector contains two glyph layers and a motor.
 *
 * Each layer contains two glyph graspers which can open, close, or alternate their states (open or
 *      close depending on the current state)
 *
 * Each glyph grasper can open and close to values that are given to it
 *
 */





package org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;



public class SpecificHardware {
    public static class JewelSorter {
        public ColorSensor color;
        Servo servo;
        double up;
        double down;
        double redCounter = 0;
        double blueCounter = 0;

        JewelSorter (ColorSensor color, Servo servo, double up, double down){
            this.color = color;
            this.servo = servo;
            this.up = up;
            this.down = down;
        }
        public boolean lower () {
            this.servo.setPosition(this.down);
            return Math.abs(this.servo.getPosition() - this.down) <= .01;
        }
        public boolean raise () {
            this.servo.setPosition(this.up);
            return true;
        }
        public String jewelColor () {
            if (this.color.red() > this.color.blue()) return "red";
            else if (this.color.blue() > this.color.red()) return "blue";
            return "null";
        }
        public String auto () {
            String color = this.jewelColor();
            if(color.equals("red")) redCounter++;
            else blueCounter++;

            if(redCounter > 30) return "red";
            else if (blueCounter > 30) return "blue";
            else return "null";
        }
        public boolean isBlue () {
            return this.color.blue() > this.color.red();
        }
        public boolean isRed () {
            return this.color.red() > this.color.blue();
        }
    }


    public static class GlyphCollector {
        DcMotor motor;


        GlyphGrasperLayer topGrasper;
        GlyphGrasperLayer bottomGrasper;


        double maximumHeight;
        double ticksPerRotation = 1;
        double spoolDiameter = 1;
        private double spoolCircumference = spoolDiameter * Math.PI;
        private double thirdHeight = maximumHeight/3;
        private double halfHeight = maximumHeight/2;
        private double twoThirdsHeight = thirdHeight*2;

        private boolean raising = false;

        double currentHeight = getCurrentMotorPosition();

        GlyphCollector (DcMotor motor, GlyphGrasperLayer topGrasper, GlyphGrasperLayer bottomGrasper,
                        double maximumHeight, double ticksPerRotation, double spoolDiameter) {
            this.motor = motor;
            this.topGrasper = topGrasper;
            this.bottomGrasper = bottomGrasper;
            this.maximumHeight = maximumHeight;
            this.ticksPerRotation = ticksPerRotation;
            this.spoolDiameter = spoolDiameter;
        }
        public double getCurrentMotorPosition () {
            if (!(this.motor == null)) {
                return this.motor.getCurrentPosition()/this.ticksPerRotation*this.spoolCircumference;
            }
            else return 0;
        }
        public boolean raiseToValue (double targetHeight) {//give a given height
            this.raising = true;
            this.raiseToValue(targetHeight);
            double difference = (targetHeight - currentHeight);//find the difference in inches
            double scalar = .5;//the scale value to determine motor speed
            this.motor.setPower(Range.clip(difference * scalar, -1, 1));
            if(Math.abs(difference) <= .1) {
                this.motor.setPower(0);
                this.raising = false;
                return true;
            }
            return false;
        }
        public void raise (double raiseValue) {
            this.motor.setPower(Range.clip(raiseValue,-1,1));
        }
        double raiseingToValue;

        public void teleRaise (Gamepad g) {
            if(this.currentHeight >= 0 && Math.abs(g.left_stick_y) > .01 &&
                    this.currentHeight <= maximumHeight)  this.raise(-g.left_stick_y/4);

            else if (-g.left_stick_y > .01 && this.currentHeight <= maximumHeight)
                this.raise(-g.left_stick_y/4);

            else if (-g.left_stick_y < -.01 && this.currentHeight >= 0) this.raise(-g.left_stick_y/4);

            else raise(0);
        }
        public void teleRaiseToPosition (Gamepad g){
            if(currentHeight < thirdHeight -1) {//
                if(g.dpad_up){
                    raiseToValue(thirdHeight);
                }
            } else if (currentHeight < twoThirdsHeight-1) {
                if(g.dpad_up){
                    raiseToValue(twoThirdsHeight);
                } else if (g.dpad_down) {
                    raiseToValue(0);
                }
            } else if (currentHeight < maximumHeight - 1) {
                if(g.dpad_up){
                    raiseToValue(maximumHeight);
                } else if (g.dpad_down) {
                    raiseToValue(thirdHeight);
                }
            }else {
                if(g.dpad_down) {
                    raiseToValue(twoThirdsHeight);
                }
            }
        }

        boolean topPressed;

        public void teleTopLayer (Gamepad g) {
            if(g.y && !topPressed){
                topPressed = true;
            } else if (!g.y && topPressed){
                topPressed = false;
                topGrasper.alternateState();
            }
        }

        boolean bottomPressed;

        public void teleBottomLayer (Gamepad g) {
            if(g.a && !bottomPressed){
                bottomPressed = true;
            } else if (!g.a && bottomPressed){
                bottomPressed = false;
                bottomGrasper.alternateState();
            }
        }
        public void teleOp (Gamepad g) {

            if (!this.raising){
                this.teleRaise(g);
                this.teleRaiseToPosition(g);
            } else {
                raiseToValue(raiseingToValue);
            }
            //this.raise(-g.right_stick_y/4);
            this.teleTopLayer(g);
            this.teleBottomLayer(g);

        }

    }



    public static class GlyphGrasperLayer {
        SingleGlyphGrasper leftGrasper;
        SingleGlyphGrasper rightGrasper;
        boolean open;
        GlyphGrasperLayer (SingleGlyphGrasper leftGrasper, SingleGlyphGrasper rightGrasper){
            this.leftGrasper = leftGrasper;
            this.rightGrasper = rightGrasper;
        }
        public void open () {
            this.leftGrasper.open();
            this.rightGrasper.open();
            this.open = true;
        }
        public void close () {
            this.leftGrasper.close();
            this.rightGrasper.close();
            this.open = false;
        }
        public void alternateState () {
            if(open) {
                this.close();
            } else {
                this.open();
            }
        }
    }
    public static class SingleGlyphGrasper {
        Servo servo;
        double openValue;
        double closeValue;
        SingleGlyphGrasper (Servo servo, double openValue, double closeValue) {
            this.servo = servo;
            this.closeValue = closeValue;
            this.openValue = openValue;
        }
        public void open () {
            this.servo.setPosition(openValue);
        }
        public void close () {
            this.servo.setPosition(closeValue);
        }
    }
}
