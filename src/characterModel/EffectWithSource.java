package characterModel;

public class EffectWithSource {
	protected String source;
	protected Integer value;
	
	public EffectWithSource(String source, Integer value) {
		this.source = source;
		this.value = value;
	}
	
	public String toString() {
		return source + ": " + value;
	}
}
