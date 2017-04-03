package me.sebdem.util;

import java.util.Random;

public class RandomUtil {
	private static Random _rng = new Random();

	public static <E> E arrayRandomItem(E[] array) {
		return array[_rng.nextInt(array.length)];
	}
	public static <E> int arrayRandomIndex(E[] array) {
		return _rng.nextInt(array.length);
	}
	public static boolean randomBool(){
		return randomBool(50);
	}
	public static boolean randomBool(float percantage){
		float value = (float)(_rng.nextDouble() * 100);
		return  value <= percantage;
	}
	
	public static Random getRandom() {
		return _rng;
	}
	public static Random setRandom(Random random) {
		_rng = random;
		return _rng;
	}
	public static Random setRandom(long seed) {
		_rng.setSeed(seed);
		return _rng;
	}

	
	public static int randomIntAround(Integer baseValue, Integer upperVariation, Integer lowerVariation){
		return (int)(_rng.nextDouble() * upperVariation.doubleValue() + baseValue - _rng.nextDouble() * lowerVariation.doubleValue() );
	}
	public static int randomIntAround(Integer baseValue, Integer upperVariation){
		return randomIntAround(baseValue, upperVariation, 0);
	}
	
	
	public static class RandomArray<E>{
		
		private E[] arrayData;
		
		public RandomArray(E... array){
			arrayData = array;
		}
		
		public E get(){
			return RandomUtil.arrayRandomItem(arrayData);
		}
		
		public E[] getArray(){
			return this.arrayData;
		}
		
	}
	
	public static class RandomArrayWrapper<E>{
		
		public RandomArray<E>[] data;
		public RandomArrayWrapper(RandomArray<E>... data){
			this.data = data;
		}
		
		@SuppressWarnings("unchecked")
		public E[] get(){
			
			E[] array = (E[])new Object[data.length];
			for(int i = 0; i < data.length; i++){
				array[i] = data[i].get();
			}
			
			return array;
		}
	}
	
}
