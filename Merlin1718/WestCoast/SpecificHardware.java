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
        private double spoolCircumference;
        private double thirdHeight;
        private double twoThirdsHeight;

        private boolean raising = false;


        GlyphCollector(DcMotor motor, GlyphGrasperLayer topGrasper, GlyphGrasperLayer bottomGrasper,
                       double maximumHeight, double ticksPerRotation, double spoolDiameter) {
            this.motor = motor;
            this.topGrasper = topGrasper;
            this.bottomGrasper = bottomGrasper;
            this.maximumHeight = maximumHeight;
            this.ticksPerRotation = ticksPerRotation;
            this.spoolDiameter = spoolDiameter;
            this.spoolCircumference = spoolDiameter * Math.PI;
            this.thirdHeight = maximumHeight / 3;
            this.twoThirdsHeight = thirdHeight * 2;
        }

        public double getCurrentMotorPosition() {
            if (!(this.motor == null)) {
                return this.motor.getCurrentPosition() / this.ticksPerRotation * this.spoolCircumference;
            } else return 0;
        }

        public double raiseingToValue = 0;

        public boolean raiseToValue(double targetHeight) {//give a given height
            this.raising = true;
            this.raiseingToValue = targetHeight;
            double difference = (targetHeight - getCurrentMotorPosition());//find the difference in inches
            double scalar = .25;//the scale value to determine motor speed
            this.raise(difference * scalar);
            if (Math.abs(difference) <= .1) {
                this.motor.setPower(0);
                this.raising = false;
                return true;
            }
            return false;
        }

        public void raise(double raiseValue) {
            this.motor.setPower(Range.clip(raiseValue, -1, 1));
        }

        public void teleRaise(Gamepad g) {
            if (this.getCurrentMotorPosition() >= 0 && Math.abs(g.left_stick_y) > .01 &&
                    this.getCurrentMotorPosition() <= maximumHeight)
                this.raise(-g.left_stick_y / 4);

            else if (-g.left_stick_y > .01 && this.getCurrentMotorPosition() <= maximumHeight)
                this.raise(-g.left_stick_y / 4);

            else if (-g.left_stick_y < -.01 && this.getCurrentMotorPosition() >= 0)
                this.raise(-g.left_stick_y / 4);

            else raise(0);
            this.closeWhenLow();
        }

        public void teleRaiseToPosition(Gamepad g) {
            if (g.a) {
                this.raiseToValue(thirdHeight);
            } else if (g.x) {
                this.raiseToValue(twoThirdsHeight);
            } else if (g.y) {
                this.raiseToValue(maximumHeight);
            }
        }

        boolean topPressed;

        public void teleTopLayer(Gamepad g) {
            if (g.left_trigger > .5 && !topPressed) {
                topPressed = true;
            } else if (!(g.left_trigger > .5) && topPressed) {
                topPressed = false;
                topGrasper.alternateState();
            }
        }

        boolean bottomPressed;

        public void teleBottomLayer(Gamepad g) {
            if (g.right_trigger > .5 && !bottomPressed) {
                bottomPressed = true;
            } else if (!(g.right_trigger > .5) && bottomPressed) {
                bottomPressed = false;
                bottomGrasper.alternateState();
            }
        }

        public void teleOp(Gamepad g) {

            if (!this.raising) {
                this.teleRaise(g);
                this.teleRaiseToPosition(g);
            } else {
                raiseToValue(raiseingToValue);
            }
            this.teleTopLayer(g);
            this.teleBottomLayer(g);

        }

        public void closeWhenLow() {
            if (this.motor.getPower() < 0 && this.getCurrentMotorPosition() < 5 && this.getCurrentMotorPosition() > 4.5) {
                this.bottomGrasper.close();
            } else if (this.motor.getPower() < 0 && this.getCurrentMotorPosition() < 4.5 && this.getCurrentMotorPosition() > 4) {
                this.bottomGrasper.open();
            }

        }
    }


    public static class GlyphGrasperLayer {
        BetterServo leftGrasper;
        BetterServo rightGrasper;
        boolean open;
        GlyphGrasperLayer (BetterServo leftGrasper, BetterServo rightGrasper){
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
    public static class BetterServo {
        Servo servo;
        double openValue;
        double closeValue;
        BetterServo (Servo servo, double openValue, double closeValue) {
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
    public static class RelicClawAndArm {
        DcMotor motor;
        double ticksPerRotation;
        BetterServo claw;
        BetterServo armServo;
        double setPosition = 0;
        RelicClawAndArm (DcMotor motor, double ticksPerRotation, BetterServo claw, BetterServo armServo){
            this.motor = motor;
            this.ticksPerRotation = ticksPerRotation;
            this.claw = claw;
            this.armServo = armServo;
        }
        public void setMotorPower (double power) {
            this.motor.setPower(Range.clip(power, -1, 1));
        }
        public double getPosition () { //0-180 = 0=1
            return motor.getCurrentPosition()/ticksPerRotation*360/180;
        }

        public void setToPosition (double position) {
            double difference = getPosition()-position;
            double differenceScalar = 1;
            this.setMotorPower(difference * differenceScalar);
        }
        public void teleOp() {

        }
        public void start(){
            setPosition = .2;
            this.setToPosition(setPosition);
            this.claw.open();
            this.armServo.open();
        }
        public void init(){

        }
    }
}
