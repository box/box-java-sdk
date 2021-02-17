package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a task on Box. Tasks can have a due date and can be assigned to a specific user.
 */
@BoxResourceType("file_request")
public class BoxFileRequest extends BoxResource {

	/**
	 * Task URL Template.
	 */
	public static final URLTemplate FILE_REQUEST_URL_TEMPLATE = new URLTemplate("file_requests/%s");
	/**
	 * Get Assignments URL Template.
	 */
	public static final URLTemplate COPY_FILE_REQUEST_URL_TEMPLATE = new URLTemplate("file_requests/%s/copy");

	/**
	 * Constructs a BoxTask for a task with a given ID.
	 *
	 * @param api the API connection to be used by the task.
	 * @param id  the ID of the task.
	 */
	public BoxFileRequest(BoxAPIConnection api, String id) {
		super(api, id);
	}

	/**
	 * Gets information about this task.
	 *
	 * @return info about this task.
	 */
	public BoxFileRequest.Info getInfo() {
		URL url = FILE_REQUEST_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
		BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
		BoxJSONResponse response = (BoxJSONResponse) request.send();
		JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
		return new Info(responseJSON);
	}

    /**
  	 * Gets information about this task.
  	 *
  	 * @return info about this task.
  	 */
  	public BoxFileRequest.Info copyInfo(BoxFileRequest.Info info, String folderId) {
  		URL url = COPY_FILE_REQUEST_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
  		BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
  		JsonObject body = info.getPendingChangesAsJsonObject();
  		JsonObject folderBody = new JsonObject();
  		folderBody.add("id", folderId);
  		folderBody.add("type", "folder");
  		body.add("folder", folderBody);
  		request.setBody(body.toString());
  		BoxJSONResponse response = (BoxJSONResponse) request.send();
  		JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
  		return new Info(jsonObject);
  	}

	/**
	 * Updates the information about this task with any info fields that have been modified locally.
	 *
	 * <p>The only fields that will be updated are the ones that have been modified locally. For example, the following
	 * code won't update any information (or even send a network request) since none of the info's fields were
	 * changed:</p>
	 *
	 * <pre>BoxTask task = new BoxTask(api, id);
	 * BoxTask.Info info = task.getInfo();
	 * task.updateInfo(info);</pre>
	 *
	 * @param info the updated info.
	 */
	public BoxFileRequest.Info updateInfo(BoxFileRequest.Info info) {
		URL url = FILE_REQUEST_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
		BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
		request.setBody(info.getPendingChanges());
		BoxJSONResponse response = (BoxJSONResponse) request.send();
		JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
		info.update(jsonObject);
		return info;
	}

	/**
	 * Gets information about this task.
	 *
	 * @return info about this task.
	 */
	public void delete() {
		URL url = FILE_REQUEST_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
		BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
		BoxAPIResponse response = request.send();
		response.disconnect();
	}

	/**
	 * Contains information about a BoxFileRequst.
	 */
	public class Info extends BoxResource.Info {
		private String type;
		private Date createdAt;
		private BoxUser.Info createdBy;
		private String description;
		private String etag;
		private Date expiresAt;
		private BoxFolder.Info folder;
		private boolean isDescriptionRequired;
		private boolean isEmailRequired;
		private Status status;
		private String title;
		private Date updatedAt;
		private BoxUser.Info updatedBy;
		private URL url;

		/**
		 * Constructs an empty Info object.
		 */
		public Info() {
			super();
		}

		/**
		 * Constructs an Info object by parsing information from a JSON string.
		 *
		 * @param json the JSON string to parse.
		 */
		public Info(String json) {
			super(json);
		}

		/**
		 * Constructs an Info object using an already parsed JSON object.
		 *
		 * @param jsonObject the parsed JSON object.
		 */
		Info(JsonObject jsonObject) {
			super(jsonObject);
		}

		@Override
		public BoxFileRequest getResource() {
			return BoxFileRequest.this;
		}

		/**
		 * Gets the file associated with this task.
		 *
		 * @return the file associated with this task.
		 */
		public String getType() {
			return this.type;
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public Date getCreatedAt() {
			return this.createdAt;
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public BoxUser.Info getCreatedBy() {
			return this.createdBy;
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public String getDescription() {
			return this.description;
		}


		public void setDescription(String description) {
			this.description = description;
            this.addPendingChange("description", description);
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public String getEtag() {
			return this.etag;
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public Date getExpiresAt() {
			return this.expiresAt;
		}

		public void setExpiresAt(Date expiresAt) {
			this.expiresAt = expiresAt;
            this.addPendingChange("expires_at", BoxDateFormat.format(expiresAt));
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public BoxFolder.Info getFolder() {
			return this.folder;
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public Boolean getIsDescriptionRequired() {
			return this.isDescriptionRequired;
		}

		public void setIsDescriptionRequired(Boolean isDescriptionRequired) {
			this.isDescriptionRequired = isDescriptionRequired;
            this.addPendingChange("is_description_required", isDescriptionRequired);
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public Boolean getIsEmailRequired() {
			return this.isEmailRequired;
		}

		public void setIsEmailRequired(Boolean isEmailRequired) {
			this.isEmailRequired = isEmailRequired;
            this.addPendingChange("is_email_required", isEmailRequired);
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public Status getStatus() {
			return this.status;
		}

		public void setStatus(Status status) {
		    this.status = status;
            this.addPendingChange("status", status.toJSONString());
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public String getTitle() {
			return this.title;
		}

		public void setTitle(String title) {
		    this.title = title;
            this.addPendingChange("title", title);
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public Date getUpdatedAt() {
			return this.updatedAt;
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public BoxUser.Info getUpdatedBy() {
			return this.updatedBy;
		}

		/**
		 * Gets the date at which this task is due.
		 *
		 * @return the date at which this task is due.
		 */
		public URL getUrl() {
			return this.url;
		}

		@Override
		void parseJSONMember(JsonObject.Member member) {
			super.parseJSONMember(member);

			String memberName = member.getName();
			JsonValue value = member.getValue();
			try {
				if (memberName.equals("type")) {
					this.type = value.asString();
				} else if (memberName.equals("created_at")) {
					this.createdAt = BoxDateFormat.parse(value.asString());
				} else if (memberName.equals("created_by")) {
					JsonObject userJSON = value.asObject();
					String userID = userJSON.get("id").asString();
					BoxUser user = new BoxUser(getAPI(), userID);
					this.createdBy = user.new Info(userJSON);
				} else if (memberName.equals("description")) {
					this.description = value.asString();
				} else if (memberName.equals("etag")) {
					this.etag = value.asString();
				} else if (memberName.equals("expires_at")) {
					this.expiresAt = BoxDateFormat.parse(value.asString());
				} else if (memberName.equals("folder")) {
					JsonObject folderJSON = value.asObject();
					String folderID = folderJSON.get("id").asString();
					BoxFolder folder = new BoxFolder(getAPI(), folderID);
					this.folder = folder.new Info(folderJSON);
				} else if (memberName.equals("is_description_required")) {
					this.isDescriptionRequired = value.asBoolean();
				} else if (memberName.equals("is_email_required")) {
					this.isEmailRequired = value.asBoolean();
				} else if (memberName.equals("status")) {
					this.status = Status.fromJSONString(value.asString());
				} else if (memberName.equals("title")) {
					this.title = value.asString();
				} else if (memberName.equals("updated_at")) {
					this.updatedAt = BoxDateFormat.parse(value.asString());
				} else if (memberName.equals("updated_by")) {
					JsonObject userJSON = value.asObject();
					String userID = userJSON.get("id").asString();
					BoxUser user = new BoxUser(getAPI(), userID);
					this.createdBy = user.new Info(userJSON);
				} else if (memberName.equals("url")) {
					try {
						String urlString = value.asString();
						this.url = new URL(urlString);
					} catch (MalformedURLException e) {
						throw new BoxAPIException("Couldn't parse url for file request", e);
					}
				}
			} catch (Exception e) {
				throw new BoxDeserializationException(memberName, value.toString(), e);
			}
		}
	}

	/**
	 * Enumerates the possible download states of a zip.
	 */
	public enum Status {
		/**
		 * Succeeded in downloading.
		 */
		ACTIVE("active"),

		/**
		 * Downloading in progress.
		 */
		INACTIVE("inactive");

		private final String jsonValue;

		private Status(String jsonValue) {
			this.jsonValue = jsonValue;
		}

		static Status fromJSONString(String jsonValue) {
			return Status.valueOf(jsonValue.toUpperCase());
		}

		String toJSONString() {
			return this.jsonValue;
		}
	}
}
