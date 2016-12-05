package org.firstinspires.ftc.teamcode.team.Other;//This might need to changed to be in a differnt folder like Merlin1 or K9Robo

//This code follows the line on the right side with the right sensor



import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CompassSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.team.Merlin1.Merlin1Hardware; //More Import statements may be needed

import java.lang.reflect.Modifier;

@TeleOp(name = "LineFollow", group = "Other")//This NEEDS to be changed tp the name of the code
@Disabled //Uncomment this if it is not wanted on the phone
public class LineFollorLeftLightLeftSide extends LinearOpMode { //The name after public class needs to be the same as the file name


    /* Declare OpMode members. */
    Merlin1Hardware robot = new Merlin1Hardware();//The hardware map needs to be the hardware map of the robot we are using

    @Override
    public void runOpMode() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        //init other variables.
        robot.Motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.Motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.Motor3.setDirection(DcMotorSimple.Direction.FORWARD);
        robot.Motor4.setDirection(DcMotorSimple.Direction.FORWARD);
        double LightError = 0;
        double LightInegral = 0;
        double LightDerivative = 0;
        double LightErrorScaled;
        double LightInegralScaled;
        double LightDerivativeScaled;
        double LightErrorScaler = 1;
        double LightIntegralScaler = 1;
        double LightDerivativeScaler = 1;
        double TargetLightValue = 0.0771358;//The left is 0.0771358 and the right is .02833764
        double CurrentLightValue = 0;
        double LastWorldLightError = 0;
        double MotorPowerMotoifier;
        double Motor1Power;
        double Motor2Power;
        double Motor3Power;
        double Motor4Power;
        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //It is ready to run
        telemetry.update();//Updates and displays data on the screen.
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            CurrentLightValue = robot.LeftLight.getLightDetected();
            LightError = TargetLightValue - CurrentLightValue;
            LightInegral = LightInegral + LightError;
            LightDerivative = LightError - LastWorldLightError;
            LastWorldLightError = LightError;
            LightErrorScaled = LightError * LightErrorScaler;
            LightInegralScaled = LightInegral * LightIntegralScaler;
            LightDerivativeScaled = LightDerivative * LightDerivativeScaler;
            MotorPowerMotoifier = LightErrorScaled + LightDerivativeScaled + LightInegralScaled;
            Motor1Power = .2 - MotorPowerMotoifier;
            Motor2Power = .2 - MotorPowerMotoifier;
            Motor3Power = .2 + MotorPowerMotoifier;
            Motor4Power = .2 + MotorPowerMotoifier;
            Motor1Power = Range.clip(Motor1Power, -1 , 1);
            Motor2Power = Range.clip(Motor2Power, -1 , 1);
            Motor3Power = Range.clip(Motor3Power, -1 , 1);
            Motor4Power = Range.clip(Motor4Power, -1 , 1);
            robot.Motor1.setPower(Motor1Power);
            robot.Motor2.setPower(Motor2Power);
            robot.Motor3.setPower(Motor3Power);
            robot.Motor4.setPower(Motor4Power);









            telemetry.addData("current light value", CurrentLightValue);
            telemetry.addData("light error", LightError);
            telemetry.addData("leght derivitatve", LightDerivative);
            telemetry.addData("light integral", LightInegral);
            telemetry.addData("motor modifier", MotorPowerMotoifier);
            telemetry.addData("motor1", Motor1Power);
            telemetry.addData("motor2", Motor2Power);
            telemetry.addData("motor3", Motor3Power);
            telemetry.addData("motor4", Motor4Power);


            telemetry.update();
            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }

}
