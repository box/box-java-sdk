package com.box.sdk;

import com.box.sdk.http.ContentType;
import com.box.sdk.http.HttpHeaders;
import com.box.sdk.http.HttpMethod;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;

/**
 *
 */
public class BoxFileUploadSession extends BoxJSONObject {

	private BoxAPIConnection api;
	private Date sessionExpiresAt;
	private String uploadSessionId;
	private Endpoints sessionEndpoints;
	private long partSize;
	private int totalParts;
	private int partsProcessed;

	private BoxAPIConnection getAPI() {
		return api;
	}

	private void setAPI(BoxAPIConnection api) {
		this.api = api;
	}

	public BoxFileUploadSession getResource() {
   		return BoxFileUploadSession.this;
	}

	public int getTotalParts() {
		return this.totalParts;
	}

	public int getPartsProcessed() {
		return this.partsProcessed;
	}

	public Date getSessionExpiresAt() {
		return sessionExpiresAt;
	}

	public String getUploadSessionId() {
		return uploadSessionId;
	}

	public Endpoints getSessionEndpoints() {
		return sessionEndpoints;
	}

	public long getPartSize() {
		return partSize;
	}

	public BoxFileUploadSession(BoxAPIConnection api, JsonObject jsonObject) {
		super(jsonObject);
		this.api = api;
	}

	protected void parseJSONMember(JsonObject.Member member) {

		String memberName = member.getName();
		JsonValue value = member.getValue();
		if (memberName.equals("session_expires_at")) {
			try {
				String dateStr = value.asString();
				this.sessionExpiresAt = BoxDateFormat.parse(dateStr.substring(0, dateStr.length()-1) + "-00:00");
			} catch (ParseException pe) {
				assert false : "A ParseException indicates a bug in the SDK.";
			}
		} else if (memberName.equals("upload_session_id")) {
			this.uploadSessionId = value.asString();
		} else if (memberName.equals("part_size")) {
			this.partSize = Double.valueOf(value.toString()).longValue();
		} else if (memberName.equals("session_endpoints")) {
			this.sessionEndpoints = new Endpoints(value.asObject());
		} else if (memberName.equals("total_parts")) {
			this.totalParts = value.asInt();
		} if (memberName.equals("num_parts_processed")) {
			this.partsProcessed = value.asInt();
		}
	}

	public class Endpoints extends BoxJSONObject {
		private URL listPartsEndpoint;
		private URL commitEndpoint;
		private URL uploadPartEndpoint;
		private URL statusEndpoint;
		private URL abortEndpoint;

		public Endpoints() {
			super();
		}

		public Endpoints(String json) {
			super(json);
		}

		Endpoints(JsonObject jsonObject) {
			super(jsonObject);
		}

		public URL getListPartsEndpoint() {
			return listPartsEndpoint;
		}

		public URL getCommitEndpoint() {
			return commitEndpoint;
		}

		public URL getUploadPartEndpoint() {
			return uploadPartEndpoint;
		}

		public URL getStatusEndpoint() {
			return statusEndpoint;
		}

		public URL getAbortEndpoint() {
			return abortEndpoint;
		}

		@Override
		protected void parseJSONMember(JsonObject.Member member) {

			String memberName = member.getName();
			JsonValue value = member.getValue();
			try {
				if (memberName.equals("list_parts")) {
					this.listPartsEndpoint = new URL(value.asString());
				} else if (memberName.equals("commit")) {
					this.commitEndpoint = new URL(value.asString());
				} else if (memberName.equals("upload_part")) {
					this.uploadPartEndpoint = new URL(value.asString());
				} else if (memberName.equals("status")) {
					this.statusEndpoint = new URL(value.asString());
				} else if (memberName.equals("abort")) {
					this.abortEndpoint = new URL(value.asString());
				}
			} catch(MalformedURLException mue) {
				assert false : "A ParseException indicates a bug in the SDK.";
			}
		}
	}

	/**
	 *
	 */
	public void uploadPart(String xBoxPartId, byte[] bytes, long offset, Long partSize, long totalSizeOfFile, String contentRangeUnits) throws MalformedURLException, NoSuchAlgorithmException {
		BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), this.getSessionEndpoints().getUploadPartEndpoint(), HttpMethod.POST);
		request.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_OCTET_STREAM);
		request.addHeader(HttpHeaders.X_BOX_PART_ID, xBoxPartId);
		byte[] digestBytes = MessageDigest.getInstance("SHA1").digest(bytes);
		String digest = Base64.encode(digestBytes);
		request.addHeader("Digest", "sha=" + digest);
		if(null == contentRangeUnits){
			contentRangeUnits = "bytes";
		}
		request.addHeader(HttpHeaders.CONTENT_RANGE, contentRangeUnits+" "+offset+"-"+(offset+partSize-1)+"/"+totalSizeOfFile);
		request.addHeader(HttpHeaders.CONTENT_LENGTH, Long.toString(bytes.length));
		request.setBody(new ByteArrayInputStream(bytes));
		BoxJSONResponse response = (BoxJSONResponse) request.send();
	}

	public BoxFileUploadSession getUploadSessionStatus(String sessionId) {
		 BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), this.getSessionEndpoints().getStatusEndpoint(), "GET");
		 BoxJSONResponse response = (BoxJSONResponse) request.send();
		 JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
		 System.out.println("Response: " + jsonObject);

		 return new BoxFileUploadSession(this.getAPI(), jsonObject);
	 }


	public void abortUploadSession(String sessionId) {
		 BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), this.getSessionEndpoints().getAbortEndpoint(), "DELETE");
		 BoxAPIResponse response = (BoxAPIResponse) request.send();
		 System.out.println("Abort session status: " + response.getResponseCode());
	 }
}
