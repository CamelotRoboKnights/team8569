package org.firstinspires.ftc.teamcode.team.K9Robo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

// Start out by writing a arcade joystick drive for the K9 - commands you should need are below
// Once your done with that try to work with the servos
// Look at other codes to be able to do that and make sure you comment your code
// remember ;
// I am still reachable so reach out to me if you are stuck
// When you are done try it on the robot

@TeleOp(name="Zack", group="Zach")

public class Test extends OpMode {

    HardweareTest robot = new HardweareTest();//The hardware map needs to be the hardware map of the robot we are using

    public void init(){
        robot.init(hardwareMap);
    }
    double neck = 0;
    double mouth = 0;
    // upon int set mouth and neck to 0

    @Override
    public void init_loop(){}
    @Override
    public void start(){}
    @Override
    public void loop(){

        double leftMotorPower;


       if (gamepad1.right_trigger>.02)
            leftMotorPower = gamepad1.right_trigger;
       else if (gamepad1.left_trigger>.02)
            leftMotorPower = -gamepad1.left_trigger;
        else
            leftMotorPower = 0;

        robot.leftMotor.setPower(leftMotorPower);
        leftMotorPower = Range.clip(leftMotorPower, -1, 1);



        telemetry.addData(
                "MotorValue",leftMotorPower);
        telemetry.addData("MotorEncoderValue",robot.leftMotor.getCurrentPosition());
    }
    @Override
    public void stop(){}


}
/*
 * Some commands to know
 *
 *  gamepad1.right_stick_x - returns the right sticks x value
 *  gamepad1.right_stick_y - returns the right sticks y value
 *  gamepad1.left_trigger - returns how far the left trigger has been pressed
 *  gamepad1.right_trigger - returns how far the left trigger has been pressed+
 *  robot.leftMotor.setPower(value); - how you set the left motor power
 *  robot.leftMotor.setPower(value); - how you set the right motor power
 *  double someName - how you create a number variable.
 *  Variable1 = Range.clip(Variable1, min value, max value); - Range clips that value
 *  robot.servo.setPosition(value); arm or claw
 *
 *
 */