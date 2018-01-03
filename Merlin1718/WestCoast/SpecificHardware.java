package org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.opencv.core.Mat;


public class SpecificHardware {
    public static class JewelSorter {
        ColorSensor color;
        Servo servo;
        double up;
        double down;
        double redJewelThreshold = 0;
        double blueJewelThreshold = 0;

        JewelSorter (ColorSensor color, Servo servo, double up, double down){
            this.color = color;
            this.servo = servo;
            this.up = up;
            this.down = down;
        }
        public boolean lower () {
            this.servo.setPosition(this.down);
            return true;
        }
        public boolean raise () {
            this.servo.setPosition(this.up);
            return true;
        }
        public String jewelColor () {
            if (this.color.red() > redJewelThreshold) return "red";
            else if (this.color.blue() > blueJewelThreshold) return "blue";
            return "null";
        }
        public boolean isBlue () {
            return this.color.blue() > blueJewelThreshold;
        }
        public boolean isRed () {
            return this.color.red() > redJewelThreshold;
        }
    }
    public static class GlyphCollector {
        DcMotor motor;


        GlyphGrasperLayer topGrasper;
        GlyphGrasperLayer bottomGrasper;

        double motorEncoderValue = getCurrentMotorPosition();

        double maximumHeight;
        double ticksPerRotation;
        double spoolDiameter;
        private double spoolCircumference = spoolDiameter * Math.PI;
        private double quarterHeight = maximumHeight/4;
        private double halfHeight = quarterHeight*2;
        private double threeQuarterHeight = quarterHeight*3;

        private boolean raising = false;

        double currentHeight = motorEncoderValue/ticksPerRotation*spoolCircumference;

        GlyphCollector (DcMotor motor, GlyphGrasperLayer topGrasper, GlyphGrasperLayer bottomGrasper,
                        double maximumHeight, double ticksPerRotation, double spoolDiameter) {
            this.motor = motor;
            this.topGrasper = topGrasper;
            this.bottomGrasper = bottomGrasper;
            this.maximumHeight = maximumHeight;
            this.ticksPerRotation = ticksPerRotation;
            this.spoolDiameter = spoolDiameter;
        }
        private double getCurrentMotorPosition () {
            if (!(this.motor == null)) {
                return this.motor.getCurrentPosition();
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
            this.motor.setPower(raiseValue);
        }
        double raiseingToValue;
        boolean topPressed;
        boolean bottomPressed;
        public void teleOp (Gamepad g) {
            if (!this.raising){
                this.raise(-g.right_stick_y);
                if(currentHeight < quarterHeight-1) {//
                    if(g.dpad_up){
                        raiseToValue(quarterHeight);
                    } else if (g.dpad_down) {
                        raiseToValue(0);
                    }
                } else if (currentHeight < halfHeight-1) {
                    if(g.dpad_up){
                        raiseToValue(halfHeight);
                    } else if (g.dpad_down) {
                        raiseToValue(quarterHeight);
                    }
                } else if (currentHeight < threeQuarterHeight-1) {
                    if(g.dpad_up){
                        raiseToValue(threeQuarterHeight);
                    } else if (g.dpad_down) {
                        raiseToValue(halfHeight);
                    }
                } else if (currentHeight < maximumHeight-1) {
                    if(g.dpad_up){
                        raiseToValue(maximumHeight);
                    } else if (g.dpad_down) {
                        raiseToValue(threeQuarterHeight);
                    }
                }
            } else {
                raiseToValue(raiseingToValue);
            }

            if(g.a && !topPressed){
                topPressed = true;
            } else if (!g.a && topPressed){
                topPressed = false;
                topGrasper.alternateState();
            }

            if(g.x && !bottomPressed){
                bottomPressed = true;
            } else if (!g.x && bottomPressed){
                bottomPressed = false;
                bottomGrasper.alternateState();
            }

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
            this.leftGrasper.open();
            this.rightGrasper.open();
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
