package model;

import org.eclipse.swt.graphics.RGB;

public enum CaptureType {
	HTML {
		@Override
		public String getColorText(RGB rgb) {
			int R = rgb.red;
			int G = rgb.green;
			int B = rgb.blue;
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append((R <= 0xf) ? ("0" + Integer.toHexString(R)) : Integer
					.toHexString(R));
			sb.append((G <= 0xf) ? ("0" + Integer.toHexString(G)) : Integer
					.toHexString(G));
			sb.append((B <= 0xf) ? ("0" + Integer.toHexString(B)) : Integer
					.toHexString(B));
			return sb.toString();
		}

	},
	RGB {

		@Override
		public String getColorText(RGB rgb) {
			int R = rgb.red;
			int G = rgb.green;
			int B = rgb.blue;
			StringBuilder sb = new StringBuilder();
			sb.append(R + ",");
			sb.append(G + ",");
			sb.append(B);
			return sb.toString();
		}

	};
	public abstract String getColorText(RGB rgb);
}
