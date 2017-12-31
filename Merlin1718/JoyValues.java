package org.firstinspires.ftc.teamcode.team.Merlin1718;


import com.qualcomm.robotcore.hardware.Gamepad;

public class JoyValues {
    public class JoyStick {
        double x;
        double y;
        double z;
        void Joystick (double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        //x & y axis is = to left stick x & y axis
        void assignValues (Gamepad g) {
            this.x = g.left_stick_x;
            this.y = g.left_stick_y;
            this.z = 0;
        }
        // This makes it so that the robot is always oriented from the driver perspective no matter what orientation the robot is at
	// 
        void makeFieldOrientated (double orientationDegrees) {
            double x = this.x;
            double y = this.y;
            double z = this.z;
            double orientationRadians = orientationDegrees * Math.PI / 180;

            this.x = -y * Math.sin(orientationRadians) + x * Math.cos(orientationRadians);
            this.y = y * Math.cos(orientationRadians) + x * Math.sin(orientationRadians);
            this.z = z;

        }
    }
    public class PolarCoordinates {
        double angle;
        double power;
        double z;

        PolarCoordinates (JoyStick joy) {
            this.power = Math.sqrt(Math.pow(joy.x, 2) + Math.pow(joy.y, 2));
            this.angle = Math.acos(joy.x / this.power);
            this.z = joy.z;
        }
    }
}
