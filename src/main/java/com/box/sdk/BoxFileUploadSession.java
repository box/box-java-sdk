package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 *
 */
public class BoxFileUploadSession extends BoxJSONObject {

	private Date sessionExpiresAt;
	private String uploadSessionId;
	private long partSize;
	private Endpoints sessionEndpoints;
	private int totalParts;
	private int partsProcessed;

	public BoxFileUploadSession() {
		super();
	}

	public BoxFileUploadSession(String json) {
		super(json);
	}

	BoxFileUploadSession(JsonObject jsonObject) {
		super(jsonObject);
	}

	public Date getSessionExpiresAt() {
		return sessionExpiresAt;
	}

	public String getUploadSessionId() {
		return uploadSessionId;
	}

	public long getPartSize() {
		return partSize;
	}

	public Endpoints getSessionEndpoints() {
		return sessionEndpoints;
	}

	public int getTotalParts() {
		return this.totalParts;
	}

	public int getPartsProcessed() {
		return this.partsProcessed;
	}

	@Override
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
}
