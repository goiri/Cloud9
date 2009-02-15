/*
 * Cloud9: A MapReduce Library for Hadoop
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package edu.umd.cloud9.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

/**
 * <p>
 * WritableComparable representing a pair of floats. The elements in the pair
 * are referred to as the left and right elements. The natural sort order is:
 * first by the left element, and then by the right element.
 * </p>
 */
public class PairOfFloats implements WritableComparable {
	private float leftElement;
	private float rightElement;

	/**
	 * Creates a pair.
	 */
	public PairOfFloats() {
	}

	/**
	 * Creates a pair.
	 * 
	 * @param left
	 *            the left element
	 * @param right
	 *            the right element
	 */
	public PairOfFloats(float left, float right) {
		set(left, right);
	}

	/**
	 * Deserializes this pair.
	 * 
	 * @param in
	 *            source for raw byte representation
	 */
	public void readFields(DataInput in) throws IOException {
		leftElement = in.readFloat();
		rightElement = in.readFloat();
	}

	/**
	 * Serializes this pair.
	 * 
	 * @param out
	 *            where to write the raw byte representation
	 */
	public void write(DataOutput out) throws IOException {
		out.writeFloat(leftElement);
		out.writeFloat(rightElement);
	}

	/**
	 * Returns the left element.
	 * 
	 * @return the left element
	 */
	public float getLeftElement() {
		return leftElement;
	}

	/**
	 * Returns the right element.
	 * 
	 * @return the right element
	 */
	public float getRightElement() {
		return rightElement;
	}

	/**
	 * Sets the right and left elements of this pair.
	 * 
	 * @param left
	 *            the left element
	 * @param right
	 *            the right element
	 */
	public void set(float left, float right) {
		leftElement = left;
		rightElement = right;
	}

	/**
	 * Checks two pairs for equality.
	 * 
	 * @param obj
	 *            object for comparison
	 * @return <code>true</code> if <code>obj</code> is equal to this
	 *         object, <code>false</code> otherwise
	 */
	public boolean equals(Object obj) {
		PairOfFloats pair = (PairOfFloats) obj;
		return leftElement == pair.getLeftElement() && rightElement == pair.getRightElement();
	}

	/**
	 * Defines a natural sort order for pairs. Pairs are sorted first by the
	 * left element, and then by the right element.
	 * 
	 * @return a value less than zero, a value greater than zero, or zero if
	 *         this pair should be sorted before, sorted after, or is equal to
	 *         <code>obj</code>.
	 */
	public int compareTo(Object obj) {
		PairOfFloats pair = (PairOfFloats) obj;

		float pl = pair.getLeftElement();
		float pr = pair.getRightElement();

		if (leftElement == pl) {
			if (rightElement < pr)
				return -1;
			if (rightElement > pr)
				return 1;
			return 0;
		}

		if (leftElement < pl)
			return -1;

		return 1;
	}

	/**
	 * Returns a hash code value for the pair.
	 * 
	 * @return hash code for the pair
	 */
	public int hashCode() {
		return (int) leftElement & (int) rightElement;
	}

	/**
	 * Generates human-readable String representation of this pair.
	 * 
	 * @return human-readable String representation of this pair
	 */
	public String toString() {
		return "(" + leftElement + ", " + rightElement + ")";
	}

	/**
	 * Clones this object.
	 * 
	 * @return clone of this object
	 */
	public PairOfFloats clone() {
		return new PairOfFloats(this.leftElement, this.rightElement);
	}

}