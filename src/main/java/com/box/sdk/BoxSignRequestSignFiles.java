package com.box.sdk;
import java.util.List;

/**
 *
 */
public class BoxSignRequestSignFiles  {
	private List<BoxFile.Info> files;
	private boolean isReadyToDownload;

	public BoxSignRequestSignFiles(List<BoxFile.Info> files, boolean isReadyToDownload) {
		this.files = files;
		this.isReadyToDownload = isReadyToDownload;
	}
}
