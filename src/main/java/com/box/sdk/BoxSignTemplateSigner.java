package com.box.sdk;

import static java.lang.String.format;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Box Sign Template signer. */
public class BoxSignTemplateSigner extends BoxJSONObject {
  private String email;
  private List<BoxSignTemplateSignerInput> inputs;
  private Boolean isInPerson;
  private int order;
  private BoxSignRequestSignerRole role;
  private String signerGroupId;
  private BoxAPIConnection api;

  /**
   * Constructs a BoxSignTemplateSigner object with the provided information.
   *
   * @param email the email.
   * @param inputs the inputs.
   * @param isInPerson whether the signer is in person or not.
   * @param order the order.
   * @param role the role.
   */
  public BoxSignTemplateSigner(
      String email,
      List<BoxSignTemplateSignerInput> inputs,
      Boolean isInPerson,
      int order,
      BoxSignRequestSignerRole role) {
    this(email, inputs, isInPerson, order, role, null);
  }

  /**
   * Constructs a BoxSignTemplateSigner object with the provided information.
   *
   * @param email the email.
   * @param inputs the inputs.
   * @param isInPerson whether the signer is in person or not.
   * @param order the order.
   * @param role the role.
   * @param signerGroupId the signer group id.
   */
  public BoxSignTemplateSigner(
      String email,
      List<BoxSignTemplateSignerInput> inputs,
      Boolean isInPerson,
      int order,
      BoxSignRequestSignerRole role,
      String signerGroupId) {
    this.email = email;
    this.inputs = inputs;
    this.isInPerson = isInPerson;
    this.order = order;
    this.role = role;
    this.signerGroupId = signerGroupId;
  }

  /**
   * Constructs a BoxSignTemplateSigner object with the provided JSON object.
   *
   * @param jsonObject the JSON object representing the Sign Template Signer.
   */
  public BoxSignTemplateSigner(JsonObject jsonObject, BoxAPIConnection api) {
    super(jsonObject);
    this.api = api;
  }

  /**
   * Gets the email of the signer.
   *
   * @return the email of the signer.
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Gets the inputs of the signer.
   *
   * @return the inputs of the signer.
   */
  public List<BoxSignTemplateSignerInput> getInputs() {
    return this.inputs;
  }

  /**
   * Used in combination with an embed URL for a sender. After the sender signs, they will be
   * redirected to the next in_person signer.
   *
   * @return true if the signer is in person; otherwise false.
   */
  public Boolean getIsInPerson() {
    return this.isInPerson;
  }

  /**
   * Gets the order of the signer.
   *
   * @return the order of the signer.
   */
  public int getOrder() {
    return this.order;
  }

  /**
   * Gets the role of the signer.
   *
   * @return the role of the signer.
   */
  public BoxSignRequestSignerRole getRole() {
    return this.role;
  }

  /**
   * Gets the signer group id. It is sufficient for only one signer from the group to sign the
   * document.
   *
   * @return the id of the group signer.
   */
  public String getSignerGroupId() {
    return this.signerGroupId;
  }

  /** {@inheritDoc} */
  @Override
  void parseJSONMember(JsonObject.Member member) {
    JsonValue value = member.getValue();
    String memberName = member.getName();
    try {
      switch (memberName) {
        case "email":
          this.email = value.asString();
          break;
        case "inputs":
          this.inputs = new ArrayList<BoxSignTemplateSignerInput>();
          for (JsonValue inputJSON : value.asArray()) {
            this.inputs.add(new BoxSignTemplateSignerInput(inputJSON.asObject(), this.api));
          }
          break;
        case "is_in_person":
          this.isInPerson = value.asBoolean();
          break;
        case "order":
          this.order = value.asInt();
          break;
        case "role":
          this.role = BoxSignRequestSignerRole.fromJSONString(value.asString());
          break;
        case "signer_group_id":
          this.signerGroupId = value.asString();
          break;
        default:
          return;
      }
    } catch (Exception e) {
      throw new BoxDeserializationException(memberName, value.toString(), e);
    }
  }

  /** Box Sign Template signer input. */
  public class BoxSignTemplateSignerInput extends BoxJSONObject {
    private BoxSignTemplateSignerInputType type;
    private Boolean checkboxValue;
    private BoxSignTemplateSignerInputContentType contentType;
    private BoxSignTemplateSignerInputCoordinates coordinates;
    private Date dateValue;
    private BoxSignTemplatesSignerInputDimensions dimensions;
    private String documentId;
    private String documentTagId;
    private List<String> dropdownChoices;
    private String groupId;
    private Boolean isRequired;
    private int pageIndex;
    private String textValue;
    private String label;
    private BoxAPIConnection api;

    /**
     * Constructs a BoxSignTemplateSignerInput object with the provided information.
     *
     * @param type the type.
     * @param checkboxValue the checkbox value.
     * @param contentType the content type.
     * @param coordinates the coordinates.
     * @param dateValue the date value.
     * @param dimensions the dimensions.
     * @param documentId the document ID.
     * @param documentTagId the document tag ID.
     * @param dropdownChoices the dropdown choices.
     * @param groupId the group ID.
     * @param isRequired whether the input is required or not.
     * @param pageIndex the page index.
     * @param textValue the text value.
     * @param label the label.
     */
    public BoxSignTemplateSignerInput(
        BoxSignTemplateSignerInputType type,
        Boolean checkboxValue,
        BoxSignTemplateSignerInputContentType contentType,
        BoxSignTemplateSignerInputCoordinates coordinates,
        Date dateValue,
        BoxSignTemplatesSignerInputDimensions dimensions,
        String documentId,
        String documentTagId,
        List<String> dropdownChoices,
        String groupId,
        Boolean isRequired,
        int pageIndex,
        String textValue,
        String label) {
      this.type = type;
      this.checkboxValue = checkboxValue;
      this.contentType = contentType;
      this.coordinates = coordinates;
      this.dateValue = dateValue;
      this.dimensions = dimensions;
      this.documentId = documentId;
      this.documentTagId = documentTagId;
      this.dropdownChoices = dropdownChoices;
      this.groupId = groupId;
      this.isRequired = isRequired;
      this.pageIndex = pageIndex;
      this.textValue = textValue;
      this.label = label;
    }

    /**
     * Constructs a BoxSignTemplateSignerInput object with the provided JSON object.
     *
     * @param jsonObject the JSON object representing the Sign Template Signer Input.
     */
    public BoxSignTemplateSignerInput(JsonObject jsonObject, BoxAPIConnection api) {
      super(jsonObject);
      this.api = api;
    }

    /**
     * Gets the type of the input.
     *
     * @return the type of the input.
     */
    public BoxSignTemplateSignerInputType getType() {
      return this.type;
    }

    /**
     * Gets the checkbox value.
     *
     * @return the checkbox value.
     */
    public Boolean getCheckboxValue() {
      return this.checkboxValue;
    }

    /**
     * Gets the content type.
     *
     * @return the content type.
     */
    public BoxSignTemplateSignerInputContentType getContentType() {
      return this.contentType;
    }

    /**
     * Gets the coordinates.
     *
     * @return the coordinates.
     */
    public BoxSignTemplateSignerInputCoordinates getCoordinates() {
      return this.coordinates;
    }

    /**
     * Gets the date value.
     *
     * @return the date value.
     */
    public Date getDateValue() {
      return this.dateValue;
    }

    /**
     * Gets the dimensions.
     *
     * @return the dimensions.
     */
    public BoxSignTemplatesSignerInputDimensions getDimensions() {
      return this.dimensions;
    }

    /**
     * Gets the document ID.
     *
     * @return the document ID.
     */
    public String getDocumentId() {
      return this.documentId;
    }

    /**
     * Gets the document tag ID.
     *
     * @return the document tag ID.
     */
    public String getDocumentTagId() {
      return this.documentTagId;
    }

    /**
     * Gets the dropdown choices.
     *
     * @return the dropdown choices.
     */
    public List<String> getDropdownChoices() {
      return this.dropdownChoices;
    }

    /**
     * Gets the group ID.
     *
     * @return the group ID.
     */
    public String getGroupId() {
      return this.groupId;
    }

    /**
     * Gets whether the input is required or not.
     *
     * @return true if the input is required; otherwise false.
     */
    public Boolean getIsRequired() {
      return this.isRequired;
    }

    /**
     * Gets the page index.
     *
     * @return the page index.
     */
    public int getPageIndex() {
      return this.pageIndex;
    }

    /**
     * Gets the text value.
     *
     * @return the text value.
     */
    public String getTextValue() {
      return this.textValue;
    }

    /**
     * Gets the label.
     *
     * @return the label.
     */
    public String getLabel() {
      return this.label;
    }

    /** {@inheritDoc} */
    @Override
    void parseJSONMember(JsonObject.Member member) {
      JsonValue value = member.getValue();
      String memberName = member.getName();
      try {
        switch (memberName) {
          case "type":
            this.type = BoxSignTemplateSignerInputType.fromJSONString(value.asString());
            break;
          case "checkbox_value":
            this.checkboxValue = value.asBoolean();
            break;
          case "content_type":
            this.contentType =
                BoxSignTemplateSignerInputContentType.fromJSONString(value.asString());
            break;
          case "coordinates":
            JsonObject coordinatesJSON = value.asObject();
            double x = coordinatesJSON.get("x").asFloat();
            double y = coordinatesJSON.get("y").asFloat();
            this.coordinates = new BoxSignTemplateSignerInputCoordinates(x, y);
            break;
          case "date_value":
            this.dateValue = BoxDateFormat.parse(value.asString());
            break;
          case "dimensions":
            JsonObject dimensionsJSON = value.asObject();
            double height = dimensionsJSON.get("height").asFloat();
            double width = dimensionsJSON.get("width").asFloat();
            this.dimensions = new BoxSignTemplatesSignerInputDimensions(height, width);
            break;
          case "document_id":
            this.documentId = value.asString();
            break;
          case "document_tag_id":
            this.documentTagId = value.asString();
            break;
          case "dropdown_choices":
            this.dropdownChoices = new ArrayList<String>();
            for (JsonValue choiceJSON : value.asArray()) {
              this.dropdownChoices.add(choiceJSON.asString());
            }
            break;
          case "group_id":
            this.groupId = value.asString();
            break;
          case "is_required":
            this.isRequired = value.asBoolean();
            break;
          case "page_index":
            this.pageIndex = value.asInt();
            break;
          case "text_value":
            this.textValue = value.asString();
            break;
          case "label":
            this.label = value.asString();
            break;
          default:
            return;
        }
      } catch (Exception e) {
        throw new BoxDeserializationException(memberName, value.toString(), e);
      }
    }
  }

  /** Box Sign Template signer input coordinates. */
  public class BoxSignTemplateSignerInputCoordinates {
    private final double x;
    private final double y;

    /**
     * Constructs a BoxSignTemplateSignerInputCoordinates object with the provided information.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public BoxSignTemplateSignerInputCoordinates(double x, double y) {
      this.x = x;
      this.y = y;
    }

    /**
     * Gets the x coordinate.
     *
     * @return the x coordinate.
     */
    public double getX() {
      return this.x;
    }

    /**
     * Gets the y coordinate.
     *
     * @return the y coordinate.
     */
    public double getY() {
      return this.y;
    }
  }

  /** Box Sign Template signer input dimensions. */
  public class BoxSignTemplatesSignerInputDimensions {
    private final double height;
    private final double width;

    /**
     * Constructs a BoxSignTemplatesSignerInputDimensions object with the provided information.
     *
     * @param height the height.
     * @param width the width.
     */
    public BoxSignTemplatesSignerInputDimensions(double height, double width) {
      this.height = height;
      this.width = width;
    }

    /**
     * Gets the height.
     *
     * @return the height.
     */
    public double getHeight() {
      return this.height;
    }

    /**
     * Gets the width.
     *
     * @return the width.
     */
    public double getWidth() {
      return this.width;
    }
  }

  /** Box Sign Template signer input type. */
  public enum BoxSignTemplateSignerInputType {
    /** Signature input type. */
    Signature("signature"),
    /** Date input type. */
    Date("date"),
    /** Text input type. */
    Text("text"),
    /** Checkbox input type. */
    Checkbox("checkbox"),
    /** Attachment input type. */
    Attachment("attachment"),
    /** Radio input type. */
    Radio("radio"),
    /** Dropdown input type. */
    Dropdown("dropdown");

    private final String jsonValue;

    BoxSignTemplateSignerInputType(String jsonValue) {
      this.jsonValue = jsonValue;
    }

    static BoxSignTemplateSignerInputType fromJSONString(String jsonValue) {
      switch (jsonValue) {
        case "signature":
          return Signature;
        case "date":
          return Date;
        case "text":
          return Text;
        case "checkbox":
          return Checkbox;
        case "attachment":
          return Attachment;
        case "radio":
          return Radio;
        case "dropdown":
          return Dropdown;
        default:
          throw new IllegalArgumentException(
              format(
                  "The provided JSON value '%s' isn't a valid BoxSignTemplateSignerInputType.",
                  jsonValue));
      }
    }
  }

  /** Box Sign Template signer input content type. */
  public enum BoxSignTemplateSignerInputContentType {
    /** Initial content type */
    Initial("initial"),
    /** Stamp content type */
    Stamp("stamp"),
    /** Signature content type */
    Signature("signature"),
    /** Company content type */
    Company("company"),
    /** Title content type */
    Title("title"),
    /** Email content type */
    Email("email"),
    /** Full name content type */
    FullName("full_name"),
    /** First name content type */
    FirstName("first_name"),
    /** Last name content type */
    LastName("last_name"),
    /** Text content type */
    Text("text"),
    /** Date content type */
    Date("date"),
    /** Checkbox content type */
    Checkbox("checkbox"),
    /** Attachement content type */
    Attachement("attachment"),
    /** Radio content type */
    Radio("radio"),
    /** Dropdown content type */
    Dropdown("dropdown");

    private final String jsonValue;

    BoxSignTemplateSignerInputContentType(String jsonValue) {
      this.jsonValue = jsonValue;
    }

    static BoxSignTemplateSignerInputContentType fromJSONString(String jsonValue) {
      switch (jsonValue) {
        case "initial":
          return Initial;
        case "stamp":
          return Stamp;
        case "signature":
          return Signature;
        case "company":
          return Company;
        case "title":
          return Title;
        case "email":
          return Email;
        case "full_name":
          return FullName;
        case "first_name":
          return FirstName;
        case "last_name":
          return LastName;
        case "text":
          return Text;
        case "date":
          return Date;
        case "checkbox":
          return Checkbox;
        case "attachment":
          return Attachement;
        case "radio":
          return Radio;
        case "dropdown":
          return Dropdown;
        default:
          throw new IllegalArgumentException(
              format(
                  "The provided JSON value '%s' isn't a valid BoxSignTemplateSignerInputContentType.",
                  jsonValue));
      }
    }
  }
}
