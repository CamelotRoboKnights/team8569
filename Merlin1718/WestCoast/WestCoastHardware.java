//This is done being commented 17-1-13

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
import org.firstinspires.ftc.teamcode.team.Merlin1718.WestCoast.WestCoastClass;


public class WestCoastHardware
{
    /* Public OpMode members. */
    private DcMotor  motorR    = null;//Right motor
    private DcMotor  motorL    = null;//Left motor
   // public DcMotor  glyph    = null;
   // public Servo leftGrasper = null;
   // public Servo rightGrasper = null;
   // public Servo rightSorter = null;
   // public ColorSensor rightColor = null;
    public BNO055IMU imu;
    public NavxMicroNavigationSensor navX;
    IntegratingGyroscope gyro;



    public Orientation angles;
    public Acceleration gravity;

    /* local OpMode members. */
    HardwareMap hwMap           =  null; //Saying this this has no opmode

    /* Constructor */
    public WestCoastHardware(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;//Seting a reference for the hardware map


        // Define and Initialize Motors
        motorR  = hwMap.dcMotor.get("motorR");//Finds the Right motor in the hardware map
        motorL  = hwMap.dcMotor.get("motorL");//Finds the Left motor in the hardware map
        //glyph = hwMap.dcMotor.get("glyph");
        //leftGrasper = hwMap.servo.get("leftGrasper");
        //rightGrasper = hwMap.servo.get("rightGrasper");
        //rightSorter = hwMap.servo.get("rightSorter");
        //rightColor = hwMap.get(ColorSensor.class, "rightColor");
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
       // glyph.setDirection(DcMotorSimple.Direction.FORWARD);
        // Set all motors to zero power
        motorR.setPower(0);//Sets the power to 0 so motors don't move
        motorL.setPower(0);
       // glyph.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       // glyph.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Define and initialize ALL installed servos.

    }
    public WestCoastClass.WestCoastDrive westCoast = new WestCoastClass.WestCoastDrive(motorL, motorR);

}

