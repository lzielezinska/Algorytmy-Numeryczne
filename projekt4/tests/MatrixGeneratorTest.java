import com.sun.org.glassfish.gmbal.Description;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixGeneratorTest {
    // ************ ComputeValues This ***************
    @Test
    public void computeValuesThis01(){
        double result = MatrixGenerator.computeToThisValue(0,1,3);
        assertEquals(1.0/3.0, result, 0.00001);
    }
    @Test
    public void computeValuesThis02(){
        double result = MatrixGenerator.computeToThisValue(0,2,3);
        assertEquals(1.0/3.0, result, 0.00001);
    }
    @Test
    public void computeValuesThis03(){
        double result = MatrixGenerator.computeToThisValue(0,3,3);
        assertEquals(1.0, result, 0.00001);
    }
    @Test
    public void computeValuesThis12(){
        double result = MatrixGenerator.computeToThisValue(1,2,3);
        assertEquals(1.0/3.0, result, 0.00001);
    }
    @Test
    public void computeValuesThis20(){
        double result = MatrixGenerator.computeToThisValue(2,0,3);
        assertEquals(1.0/3.0, result, 0.00001);
    }
    @Test
    public void computeValuesThis30(){
        double result = MatrixGenerator.computeToThisValue(3,0,3);
        assertEquals(1.0, result, 0.00001);
    }

    // ************ ComputeValues P ***************
    @Test
    public void computeValuesP10(){
        double result = MatrixGenerator.computeToPValue(1,0,3);
        assertEquals(2.0/3.0, result, 0.00001);
    }
    @Test
    public void computeValuesP11(){
        double result = MatrixGenerator.computeToPValue(1,1,3);
        assertEquals(1.0/3.0, result, 0.00001);
    }
    @Test
    public void computeValuesP20(){
        double result = MatrixGenerator.computeToPValue(2,0,3);
        assertEquals(2.0/3.0, result, 0.00001);
    }

    // ************ ComputeValues U ***************
    @Test
    public void computeValuesU20(){
        double result = MatrixGenerator.computeToUValue(2,1,3);
        assertEquals(2.0/3.0, result, 0.00001);
    }
    @Test
    public void computeValuesU11(){
        double result = MatrixGenerator.computeToUValue(1,1,3);
        assertEquals(1.0/3.0, result, 0.00001);
    }
    @Test
    public void computeValuesU30(){
        double result = MatrixGenerator.computeToUValue(3,0,3);
        assertEquals(0.0/3.0, result, 0.00001);
    }

    // ************ Find Element ****************
    @Test
    public void findElement00(){
        int result = MatrixGenerator.findElement(0,0, 3);
        assertEquals(0, result);
    }
    @Test
    public void findElement01(){
        int result = MatrixGenerator.findElement(0,1, 3);
        assertEquals(1, result);
    }
    @Test
    public void findElement02(){
        int result = MatrixGenerator.findElement(0,2, 3);
        assertEquals(2, result);
    }
    @Test
    public void findElement03(){
        int result = MatrixGenerator.findElement(0,3, 3);
        assertEquals(3, result);
    }
    @Test
    public void findElement10(){
        int result = MatrixGenerator.findElement(1,0, 3);
        assertEquals(4, result);
    }
    @Test
    public void findElement11(){
        int result = MatrixGenerator.findElement(1,1, 3);
        assertEquals(5, result);
    }
    @Test
    public void findElement12(){
        int result = MatrixGenerator.findElement(1,2, 3);
        assertEquals(6, result);
    }
    @Test
    public void findElement20(){
        int result = MatrixGenerator.findElement(2,0, 3);
        assertEquals(7, result);
    }
    @Test
    public void findElement21(){
        int result = MatrixGenerator.findElement(2,1, 3);
        assertEquals(8, result);
    }
    @Test
    public void findElement30(){
        int result = MatrixGenerator.findElement(3,0, 3);
        assertEquals(9, result);
    }
    @Test
    public void findElement31(){
        int result = MatrixGenerator.findElement(3,1, 4);
        assertEquals(13, result);
    }
    @Test
    public void findElement304(){
        int result = MatrixGenerator.findElement(3,0, 4);
        assertEquals(12, result);
    }
    @Test
    public void findElement004(){
        int result = MatrixGenerator.findElement(0,0, 4);
        assertEquals(0, result);
    }


    // ************ Find Element * END ***************
    // ************ Find U Element ****************
    @Test
    public void getToUElement11() {
        int result = MatrixGenerator.getToUElement(1,1, 3);
        assertEquals(0, result);
    }
    @Test
    public void getToUElement01() {
        int result = MatrixGenerator.getToUElement(0,1, 3);
        assertEquals(-1, result);
    }
    @Test
    public void getToUElement21() {
        int result = MatrixGenerator.getToUElement(2,1, 3);
        assertEquals(4, result);
    }
    @Test
    public void getToUElement12() {
        int result = MatrixGenerator.getToUElement(1,2, 3);
        assertEquals(1, result);
    }

    // ************ Find U Element * END ***************
    // ************ Find P Element ****************

    @Test
    public void getToPElement00() {
        int result = MatrixGenerator.getToPElement(0,0, 3);
        assertEquals(4, result);
    }
    @Test
    public void getToPElement11() {
        int result = MatrixGenerator.getToPElement(1,1, 3);
        assertEquals(8, result);
    }
    @Test
    public void getToPElement21() {
        int result = MatrixGenerator.getToPElement(2,1, 3);
        assertEquals(-1, result);
    }
    @Test
    public void getToPElement10() {
        int result = MatrixGenerator.getToPElement(1,0, 3);
        assertEquals(7, result);
    }
    // ************ Find P Element * END ***************
    // ************ Find N Element ****************
    @Test
    public void getToNElement10() {
        int result = MatrixGenerator.getToNElement(1,0, 3);
        assertEquals(5, result);
    }
    @Test
    public void getToNElement03() {
        int result = MatrixGenerator.getToNElement(0,3, 3);
        assertEquals(-1, result);
    }
    @Test
    public void getToNElement21() {
        int result = MatrixGenerator.getToNElement(2,1, 3);
        assertEquals(-1, result);
    }
    @Test
    public void getToNElement01() {
        int result = MatrixGenerator.getToNElement(0,1, 3);
        assertEquals(2, result);
    }
    // ************ Find N Element * END ***************
    // ************ Find This Element ****************
    @Test
    public void getToThisElement00() {
        int result = MatrixGenerator.getToThisElement(0,0, 3);
        assertEquals(0, result);
    }
    @Test
    public void getToThisElement12() {
        int result = MatrixGenerator.getToThisElement(1,2, 3);
        assertEquals(6, result);
    }
    @Test
    public void getToThisElement03() {
        int result = MatrixGenerator.getToThisElement(3,0, 3);
        assertEquals(9, result);
    }
    // ************ Find This Element * END ***************



}