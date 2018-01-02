package org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast;

import android.widget.GridLayout;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class WestCoastHardware {

    private DcMotor  motorR    = null;
    private DcMotor  motorL    = null;
    private DcMotor  glyph    = null;
    private Servo leftTopGrasperServo = null;
    private Servo rightTopGrasperServo = null;
    private Servo leftBottomGrasperServo = null;
    private Servo rightBottomGrasperServo = null;

    private Servo sorter = null;
    private ColorSensor colorSensor = null;
    private BNO055IMU imu;
    private NavxMicroNavigationSensor navX;
    IntegratingGyroscope gyro;



    private Orientation angles;
    private Acceleration gravity;

    /* local OpMode members. */
    HardwareMap hwMap           =  null; //Saying this this has no opmode

    /* Constructor */
    public WestCoastHardware(){}

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;//Seting a reference for the hardware map


        // Define and Initialize Motors
        motorR  = hwMap.dcMotor.get("motorR");//Finds the Right motor in the hardware map
        motorL  = hwMap.dcMotor.get("motorL");//Finds the Left motor in the hardware map
        glyph = hwMap.dcMotor.get("glyph");
        leftTopGrasperServo = hwMap.servo.get("leftTopGrasper");
        rightTopGrasperServo = hwMap.servo.get("rightTopGrasper");
        leftBottomGrasperServo = hwMap.servo.get("leftBottomGrasper");
        rightBottomGrasperServo = hwMap.servo.get("rightBottomGrasper");
        sorter = hwMap.servo.get("sorter");
        colorSensor = hwMap.get(ColorSensor.class, "color");
        navX = hwMap.get(NavxMicroNavigationSensor.class, "navx");
        gyro = (IntegratingGyroscope)navX;




        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hwMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);





        motorR.setDirection(DcMotorSimple.Direction.FORWARD);//Sets the motor power to positive because duh.
        motorL.setDirection(DcMotorSimple.Direction.FORWARD);//Sets the motor power as positive because duh.
        glyph.setDirection(DcMotorSimple.Direction.FORWARD);

        motorR.setPower(0);//Sets the power to 0 so motors don't move
        motorL.setPower(0);
        glyph.setPower(0);

        motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        glyph.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }
    private double glyphCollectorMaxHeight = 10;
    private double glyphCollectorTicksPerRotation = 1220;
    private double glyphCollectorSpoolDiameter = 1;


    public WestCoastClass.WestCoastDrive westCoast =
            new WestCoastClass.WestCoastDrive(motorL, motorR);

    public SpecificHardware.JewelSorter jewelSorter =
            new SpecificHardware.JewelSorter(colorSensor, sorter, 0, 1);


    private SpecificHardware.SingleGlyphGrasper topLeftGrasper =
            new SpecificHardware.SingleGlyphGrasper(leftTopGrasperServo, 0, 1);

    private SpecificHardware.SingleGlyphGrasper topRightGrasper =
            new SpecificHardware.SingleGlyphGrasper(rightTopGrasperServo, 1, 0);

    private SpecificHardware.SingleGlyphGrasper bottomLeftGrasper =
            new SpecificHardware.SingleGlyphGrasper(leftBottomGrasperServo, 0, 1);

    private SpecificHardware.SingleGlyphGrasper bottomRightGrasper =
            new SpecificHardware.SingleGlyphGrasper(rightBottomGrasperServo, 1, 0);

    private SpecificHardware.GlyphGrasperLayer topGlyphLayer =
            new SpecificHardware.GlyphGrasperLayer(topLeftGrasper, topRightGrasper);

    private SpecificHardware.GlyphGrasperLayer bottomGlyphLayer =
            new SpecificHardware.GlyphGrasperLayer(bottomLeftGrasper, bottomRightGrasper);


    public SpecificHardware.GlyphCollector glyphCollector =
            new SpecificHardware.GlyphCollector(glyph, topGlyphLayer, bottomGlyphLayer,
                    glyphCollectorMaxHeight, glyphCollectorTicksPerRotation,
                    glyphCollectorSpoolDiameter);
            //motor, leftGrasper, rightGrasper, maxHeight, ticksPerRotation, spoolDiameter.

}