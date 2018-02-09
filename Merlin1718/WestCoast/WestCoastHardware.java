
package org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast;

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

    public DcMotor  motorR    = null;
    public DcMotor  motorL    = null;
    public DcMotor relicMotor = null;
    private DcMotor  glyph    = null;
    private Servo leftTopGrasperServo = null;
    private Servo rightTopGrasperServo = null;
    private Servo leftBottomGrasperServo = null;
    private Servo rightBottomGrasperServo = null;
    private Servo clawServo = null;
    private Servo armServo = null;

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
        relicMotor = hwMap.dcMotor.get("relic");
        leftTopGrasperServo = hwMap.servo.get("leftTopGrasper");
        rightTopGrasperServo = hwMap.servo.get("rightTopGrasper");
        leftBottomGrasperServo = hwMap.servo.get("leftBottomGrasper");
        rightBottomGrasperServo = hwMap.servo.get("rightBottomGrasper");
        clawServo = hwMap.servo.get("claw");
        armServo = hwMap.servo.get("arm");

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
        motorL.setDirection(DcMotorSimple.Direction.REVERSE);//Sets the motor power as positive because duh.
        glyph.setDirection(DcMotorSimple.Direction.REVERSE);
        relicMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        motorR.setPower(0);//Sets the power to 0 so motors don't move
        motorL.setPower(0);
        glyph.setPower(0);
        relicMotor.setPower(0);



        motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        glyph.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        relicMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        westCoast = new WestCoastClass.WestCoastDrive(motorL, motorR);

        jewelSorter = new SpecificHardware.JewelSorter(colorSensor, sorter, .8, .1);// Up down

        topLeftGrasper = new SpecificHardware.BetterServo(leftTopGrasperServo, .15, 0);//Open close
        topRightGrasper = new SpecificHardware.BetterServo(rightTopGrasperServo, .75, 1);
        bottomLeftGrasper = new SpecificHardware.BetterServo(leftBottomGrasperServo, .2, 0);
        bottomRightGrasper= new SpecificHardware.BetterServo(rightBottomGrasperServo, .8, 1);

        topGlyphLayer = new SpecificHardware.GlyphGrasperLayer(topLeftGrasper, topRightGrasper);
        bottomGlyphLayer = new SpecificHardware.GlyphGrasperLayer(bottomLeftGrasper, bottomRightGrasper);

        glyphCollector = new SpecificHardware.GlyphCollector(glyph, topGlyphLayer, bottomGlyphLayer,
                        glyphCollectorMaxHeight, glyphCollectorTicksPerRotation,
                        glyphCollectorSpoolDiameter);
        //motor, leftGrasper, rightGrasper, maxHeight, ticksPerRotation, spoolDiameter.

        revIMU = new Sensors.RevIMU(imu);
        navx = new Sensors.Navx(navX);
        motoG = new Sensors.Phone();


        relicArm = new SpecificHardware.BetterServo(armServo, 1, 0);//open close;
        relicClaw = new SpecificHardware.BetterServo(clawServo, 0, 1);
        relic = new SpecificHardware.RelicClawAndArm(relicMotor, glyphCollectorTicksPerRotation, relicClaw, relicArm, 28, .98);
    }
    private double glyphCollectorMaxHeight = 16;
    private double glyphCollectorTicksPerRotation = 1220;
    private double glyphCollectorSpoolDiameter = 1;


    public WestCoastClass.WestCoastDrive westCoast;

    public SpecificHardware.JewelSorter jewelSorter;

    private SpecificHardware.BetterServo topLeftGrasper;
    private SpecificHardware.BetterServo topRightGrasper;
    private SpecificHardware.BetterServo bottomLeftGrasper;
    private SpecificHardware.BetterServo bottomRightGrasper;
    private SpecificHardware.BetterServo relicArm;
    private SpecificHardware.BetterServo relicClaw;

    private SpecificHardware.GlyphGrasperLayer topGlyphLayer;
    private SpecificHardware.GlyphGrasperLayer bottomGlyphLayer;

    public SpecificHardware.GlyphCollector glyphCollector;

    public Sensors.RevIMU revIMU;
    public Sensors.Navx navx;
    public Sensors.Phone motoG;

    public SpecificHardware.RelicClawAndArm relic;
}