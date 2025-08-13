package com.box.sdkgen.client;

import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import com.box.sdkgen.managers.ai.AiManager;
import com.box.sdkgen.managers.aistudio.AiStudioManager;
import com.box.sdkgen.managers.appitemassociations.AppItemAssociationsManager;
import com.box.sdkgen.managers.archives.ArchivesManager;
import com.box.sdkgen.managers.authorization.AuthorizationManager;
import com.box.sdkgen.managers.avatars.AvatarsManager;
import com.box.sdkgen.managers.chunkeduploads.ChunkedUploadsManager;
import com.box.sdkgen.managers.classifications.ClassificationsManager;
import com.box.sdkgen.managers.collaborationallowlistentries.CollaborationAllowlistEntriesManager;
import com.box.sdkgen.managers.collaborationallowlistexempttargets.CollaborationAllowlistExemptTargetsManager;
import com.box.sdkgen.managers.collections.CollectionsManager;
import com.box.sdkgen.managers.comments.CommentsManager;
import com.box.sdkgen.managers.devicepinners.DevicePinnersManager;
import com.box.sdkgen.managers.docgen.DocgenManager;
import com.box.sdkgen.managers.docgentemplate.DocgenTemplateManager;
import com.box.sdkgen.managers.downloads.DownloadsManager;
import com.box.sdkgen.managers.emailaliases.EmailAliasesManager;
import com.box.sdkgen.managers.events.EventsManager;
import com.box.sdkgen.managers.fileclassifications.FileClassificationsManager;
import com.box.sdkgen.managers.filemetadata.FileMetadataManager;
import com.box.sdkgen.managers.filerequests.FileRequestsManager;
import com.box.sdkgen.managers.files.FilesManager;
import com.box.sdkgen.managers.fileversionlegalholds.FileVersionLegalHoldsManager;
import com.box.sdkgen.managers.fileversionretentions.FileVersionRetentionsManager;
import com.box.sdkgen.managers.fileversions.FileVersionsManager;
import com.box.sdkgen.managers.filewatermarks.FileWatermarksManager;
import com.box.sdkgen.managers.folderclassifications.FolderClassificationsManager;
import com.box.sdkgen.managers.folderlocks.FolderLocksManager;
import com.box.sdkgen.managers.foldermetadata.FolderMetadataManager;
import com.box.sdkgen.managers.folders.FoldersManager;
import com.box.sdkgen.managers.folderwatermarks.FolderWatermarksManager;
import com.box.sdkgen.managers.groups.GroupsManager;
import com.box.sdkgen.managers.hubcollaborations.HubCollaborationsManager;
import com.box.sdkgen.managers.hubitems.HubItemsManager;
import com.box.sdkgen.managers.hubs.HubsManager;
import com.box.sdkgen.managers.integrationmappings.IntegrationMappingsManager;
import com.box.sdkgen.managers.invites.InvitesManager;
import com.box.sdkgen.managers.legalholdpolicies.LegalHoldPoliciesManager;
import com.box.sdkgen.managers.legalholdpolicyassignments.LegalHoldPolicyAssignmentsManager;
import com.box.sdkgen.managers.listcollaborations.ListCollaborationsManager;
import com.box.sdkgen.managers.memberships.MembershipsManager;
import com.box.sdkgen.managers.metadatacascadepolicies.MetadataCascadePoliciesManager;
import com.box.sdkgen.managers.metadatatemplates.MetadataTemplatesManager;
import com.box.sdkgen.managers.recentitems.RecentItemsManager;
import com.box.sdkgen.managers.retentionpolicies.RetentionPoliciesManager;
import com.box.sdkgen.managers.retentionpolicyassignments.RetentionPolicyAssignmentsManager;
import com.box.sdkgen.managers.search.SearchManager;
import com.box.sdkgen.managers.sessiontermination.SessionTerminationManager;
import com.box.sdkgen.managers.sharedlinksappitems.SharedLinksAppItemsManager;
import com.box.sdkgen.managers.sharedlinksfiles.SharedLinksFilesManager;
import com.box.sdkgen.managers.sharedlinksfolders.SharedLinksFoldersManager;
import com.box.sdkgen.managers.sharedlinksweblinks.SharedLinksWebLinksManager;
import com.box.sdkgen.managers.shieldinformationbarrierreports.ShieldInformationBarrierReportsManager;
import com.box.sdkgen.managers.shieldinformationbarriers.ShieldInformationBarriersManager;
import com.box.sdkgen.managers.shieldinformationbarriersegmentmembers.ShieldInformationBarrierSegmentMembersManager;
import com.box.sdkgen.managers.shieldinformationbarriersegmentrestrictions.ShieldInformationBarrierSegmentRestrictionsManager;
import com.box.sdkgen.managers.shieldinformationbarriersegments.ShieldInformationBarrierSegmentsManager;
import com.box.sdkgen.managers.shieldlists.ShieldListsManager;
import com.box.sdkgen.managers.signrequests.SignRequestsManager;
import com.box.sdkgen.managers.signtemplates.SignTemplatesManager;
import com.box.sdkgen.managers.skills.SkillsManager;
import com.box.sdkgen.managers.storagepolicies.StoragePoliciesManager;
import com.box.sdkgen.managers.storagepolicyassignments.StoragePolicyAssignmentsManager;
import com.box.sdkgen.managers.taskassignments.TaskAssignmentsManager;
import com.box.sdkgen.managers.tasks.TasksManager;
import com.box.sdkgen.managers.termsofservices.TermsOfServicesManager;
import com.box.sdkgen.managers.termsofserviceuserstatuses.TermsOfServiceUserStatusesManager;
import com.box.sdkgen.managers.transfer.TransferManager;
import com.box.sdkgen.managers.trashedfiles.TrashedFilesManager;
import com.box.sdkgen.managers.trashedfolders.TrashedFoldersManager;
import com.box.sdkgen.managers.trasheditems.TrashedItemsManager;
import com.box.sdkgen.managers.trashedweblinks.TrashedWebLinksManager;
import com.box.sdkgen.managers.uploads.UploadsManager;
import com.box.sdkgen.managers.usercollaborations.UserCollaborationsManager;
import com.box.sdkgen.managers.users.UsersManager;
import com.box.sdkgen.managers.webhooks.WebhooksManager;
import com.box.sdkgen.managers.weblinks.WebLinksManager;
import com.box.sdkgen.managers.workflows.WorkflowsManager;
import com.box.sdkgen.managers.zipdownloads.ZipDownloadsManager;
import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.baseurls.BaseUrls;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.interceptors.Interceptor;
import com.box.sdkgen.networking.network.NetworkSession;
import java.util.List;
import java.util.Map;

public class BoxClient {

  public final Authentication auth;

  public NetworkSession networkSession;

  public final AuthorizationManager authorization;

  public final FilesManager files;

  public final TrashedFilesManager trashedFiles;

  public final AppItemAssociationsManager appItemAssociations;

  public final DownloadsManager downloads;

  public final UploadsManager uploads;

  public final ChunkedUploadsManager chunkedUploads;

  public final ListCollaborationsManager listCollaborations;

  public final CommentsManager comments;

  public final TasksManager tasks;

  public final FileVersionsManager fileVersions;

  public final FileMetadataManager fileMetadata;

  public final FileClassificationsManager fileClassifications;

  public final SkillsManager skills;

  public final FileWatermarksManager fileWatermarks;

  public final FileRequestsManager fileRequests;

  public final FoldersManager folders;

  public final TrashedFoldersManager trashedFolders;

  public final FolderMetadataManager folderMetadata;

  public final FolderClassificationsManager folderClassifications;

  public final TrashedItemsManager trashedItems;

  public final FolderWatermarksManager folderWatermarks;

  public final FolderLocksManager folderLocks;

  public final MetadataTemplatesManager metadataTemplates;

  public final ClassificationsManager classifications;

  public final MetadataCascadePoliciesManager metadataCascadePolicies;

  public final SearchManager search;

  public final UserCollaborationsManager userCollaborations;

  public final TaskAssignmentsManager taskAssignments;

  public final SharedLinksFilesManager sharedLinksFiles;

  public final SharedLinksFoldersManager sharedLinksFolders;

  public final WebLinksManager webLinks;

  public final TrashedWebLinksManager trashedWebLinks;

  public final SharedLinksWebLinksManager sharedLinksWebLinks;

  public final SharedLinksAppItemsManager sharedLinksAppItems;

  public final UsersManager users;

  public final SessionTerminationManager sessionTermination;

  public final AvatarsManager avatars;

  public final TransferManager transfer;

  public final EmailAliasesManager emailAliases;

  public final MembershipsManager memberships;

  public final InvitesManager invites;

  public final GroupsManager groups;

  public final WebhooksManager webhooks;

  public final EventsManager events;

  public final CollectionsManager collections;

  public final RecentItemsManager recentItems;

  public final RetentionPoliciesManager retentionPolicies;

  public final RetentionPolicyAssignmentsManager retentionPolicyAssignments;

  public final LegalHoldPoliciesManager legalHoldPolicies;

  public final LegalHoldPolicyAssignmentsManager legalHoldPolicyAssignments;

  public final FileVersionRetentionsManager fileVersionRetentions;

  public final FileVersionLegalHoldsManager fileVersionLegalHolds;

  public final ShieldInformationBarriersManager shieldInformationBarriers;

  public final ShieldInformationBarrierReportsManager shieldInformationBarrierReports;

  public final ShieldInformationBarrierSegmentsManager shieldInformationBarrierSegments;

  public final ShieldInformationBarrierSegmentMembersManager shieldInformationBarrierSegmentMembers;

  public final ShieldInformationBarrierSegmentRestrictionsManager
      shieldInformationBarrierSegmentRestrictions;

  public final DevicePinnersManager devicePinners;

  public final TermsOfServicesManager termsOfServices;

  public final TermsOfServiceUserStatusesManager termsOfServiceUserStatuses;

  public final CollaborationAllowlistEntriesManager collaborationAllowlistEntries;

  public final CollaborationAllowlistExemptTargetsManager collaborationAllowlistExemptTargets;

  public final StoragePoliciesManager storagePolicies;

  public final StoragePolicyAssignmentsManager storagePolicyAssignments;

  public final ZipDownloadsManager zipDownloads;

  public final SignRequestsManager signRequests;

  public final WorkflowsManager workflows;

  public final SignTemplatesManager signTemplates;

  public final IntegrationMappingsManager integrationMappings;

  public final AiManager ai;

  public final AiStudioManager aiStudio;

  public final DocgenTemplateManager docgenTemplate;

  public final DocgenManager docgen;

  public final HubsManager hubs;

  public final HubCollaborationsManager hubCollaborations;

  public final HubItemsManager hubItems;

  public final ShieldListsManager shieldLists;

  public final ArchivesManager archives;

  public BoxClient(Authentication auth) {
    this.auth = auth;
    this.networkSession = new NetworkSession.Builder().baseUrls(new BaseUrls()).build();
    this.authorization =
        new AuthorizationManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.files =
        new FilesManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.trashedFiles =
        new TrashedFilesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.appItemAssociations =
        new AppItemAssociationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.downloads =
        new DownloadsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.uploads =
        new UploadsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.chunkedUploads =
        new ChunkedUploadsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.listCollaborations =
        new ListCollaborationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.comments =
        new CommentsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.tasks =
        new TasksManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.fileVersions =
        new FileVersionsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.fileMetadata =
        new FileMetadataManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.fileClassifications =
        new FileClassificationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.skills =
        new SkillsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.fileWatermarks =
        new FileWatermarksManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.fileRequests =
        new FileRequestsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.folders =
        new FoldersManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.trashedFolders =
        new TrashedFoldersManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.folderMetadata =
        new FolderMetadataManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.folderClassifications =
        new FolderClassificationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.trashedItems =
        new TrashedItemsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.folderWatermarks =
        new FolderWatermarksManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.folderLocks =
        new FolderLocksManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.metadataTemplates =
        new MetadataTemplatesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.classifications =
        new ClassificationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.metadataCascadePolicies =
        new MetadataCascadePoliciesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.search =
        new SearchManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.userCollaborations =
        new UserCollaborationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.taskAssignments =
        new TaskAssignmentsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.sharedLinksFiles =
        new SharedLinksFilesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.sharedLinksFolders =
        new SharedLinksFoldersManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.webLinks =
        new WebLinksManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.trashedWebLinks =
        new TrashedWebLinksManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.sharedLinksWebLinks =
        new SharedLinksWebLinksManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.sharedLinksAppItems =
        new SharedLinksAppItemsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.users =
        new UsersManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.sessionTermination =
        new SessionTerminationManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.avatars =
        new AvatarsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.transfer =
        new TransferManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.emailAliases =
        new EmailAliasesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.memberships =
        new MembershipsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.invites =
        new InvitesManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.groups =
        new GroupsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.webhooks =
        new WebhooksManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.events =
        new EventsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.collections =
        new CollectionsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.recentItems =
        new RecentItemsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.retentionPolicies =
        new RetentionPoliciesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.retentionPolicyAssignments =
        new RetentionPolicyAssignmentsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.legalHoldPolicies =
        new LegalHoldPoliciesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.legalHoldPolicyAssignments =
        new LegalHoldPolicyAssignmentsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.fileVersionRetentions =
        new FileVersionRetentionsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.fileVersionLegalHolds =
        new FileVersionLegalHoldsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.shieldInformationBarriers =
        new ShieldInformationBarriersManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.shieldInformationBarrierReports =
        new ShieldInformationBarrierReportsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.shieldInformationBarrierSegments =
        new ShieldInformationBarrierSegmentsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.shieldInformationBarrierSegmentMembers =
        new ShieldInformationBarrierSegmentMembersManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.shieldInformationBarrierSegmentRestrictions =
        new ShieldInformationBarrierSegmentRestrictionsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.devicePinners =
        new DevicePinnersManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.termsOfServices =
        new TermsOfServicesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.termsOfServiceUserStatuses =
        new TermsOfServiceUserStatusesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.collaborationAllowlistEntries =
        new CollaborationAllowlistEntriesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.collaborationAllowlistExemptTargets =
        new CollaborationAllowlistExemptTargetsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.storagePolicies =
        new StoragePoliciesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.storagePolicyAssignments =
        new StoragePolicyAssignmentsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.zipDownloads =
        new ZipDownloadsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.signRequests =
        new SignRequestsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.workflows =
        new WorkflowsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.signTemplates =
        new SignTemplatesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.integrationMappings =
        new IntegrationMappingsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.ai = new AiManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.aiStudio =
        new AiStudioManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.docgenTemplate =
        new DocgenTemplateManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.docgen =
        new DocgenManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.hubs =
        new HubsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.hubCollaborations =
        new HubCollaborationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.hubItems =
        new HubItemsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.shieldLists =
        new ShieldListsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.archives =
        new ArchivesManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
  }

  protected BoxClient(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
    this.authorization =
        new AuthorizationManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.files =
        new FilesManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.trashedFiles =
        new TrashedFilesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.appItemAssociations =
        new AppItemAssociationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.downloads =
        new DownloadsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.uploads =
        new UploadsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.chunkedUploads =
        new ChunkedUploadsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.listCollaborations =
        new ListCollaborationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.comments =
        new CommentsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.tasks =
        new TasksManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.fileVersions =
        new FileVersionsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.fileMetadata =
        new FileMetadataManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.fileClassifications =
        new FileClassificationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.skills =
        new SkillsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.fileWatermarks =
        new FileWatermarksManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.fileRequests =
        new FileRequestsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.folders =
        new FoldersManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.trashedFolders =
        new TrashedFoldersManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.folderMetadata =
        new FolderMetadataManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.folderClassifications =
        new FolderClassificationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.trashedItems =
        new TrashedItemsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.folderWatermarks =
        new FolderWatermarksManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.folderLocks =
        new FolderLocksManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.metadataTemplates =
        new MetadataTemplatesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.classifications =
        new ClassificationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.metadataCascadePolicies =
        new MetadataCascadePoliciesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.search =
        new SearchManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.userCollaborations =
        new UserCollaborationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.taskAssignments =
        new TaskAssignmentsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.sharedLinksFiles =
        new SharedLinksFilesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.sharedLinksFolders =
        new SharedLinksFoldersManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.webLinks =
        new WebLinksManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.trashedWebLinks =
        new TrashedWebLinksManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.sharedLinksWebLinks =
        new SharedLinksWebLinksManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.sharedLinksAppItems =
        new SharedLinksAppItemsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.users =
        new UsersManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.sessionTermination =
        new SessionTerminationManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.avatars =
        new AvatarsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.transfer =
        new TransferManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.emailAliases =
        new EmailAliasesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.memberships =
        new MembershipsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.invites =
        new InvitesManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.groups =
        new GroupsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.webhooks =
        new WebhooksManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.events =
        new EventsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.collections =
        new CollectionsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.recentItems =
        new RecentItemsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.retentionPolicies =
        new RetentionPoliciesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.retentionPolicyAssignments =
        new RetentionPolicyAssignmentsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.legalHoldPolicies =
        new LegalHoldPoliciesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.legalHoldPolicyAssignments =
        new LegalHoldPolicyAssignmentsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.fileVersionRetentions =
        new FileVersionRetentionsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.fileVersionLegalHolds =
        new FileVersionLegalHoldsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.shieldInformationBarriers =
        new ShieldInformationBarriersManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.shieldInformationBarrierReports =
        new ShieldInformationBarrierReportsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.shieldInformationBarrierSegments =
        new ShieldInformationBarrierSegmentsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.shieldInformationBarrierSegmentMembers =
        new ShieldInformationBarrierSegmentMembersManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.shieldInformationBarrierSegmentRestrictions =
        new ShieldInformationBarrierSegmentRestrictionsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.devicePinners =
        new DevicePinnersManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.termsOfServices =
        new TermsOfServicesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.termsOfServiceUserStatuses =
        new TermsOfServiceUserStatusesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.collaborationAllowlistEntries =
        new CollaborationAllowlistEntriesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.collaborationAllowlistExemptTargets =
        new CollaborationAllowlistExemptTargetsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.storagePolicies =
        new StoragePoliciesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.storagePolicyAssignments =
        new StoragePolicyAssignmentsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.zipDownloads =
        new ZipDownloadsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.signRequests =
        new SignRequestsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.workflows =
        new WorkflowsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.signTemplates =
        new SignTemplatesManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.integrationMappings =
        new IntegrationMappingsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.ai = new AiManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.aiStudio =
        new AiStudioManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.docgenTemplate =
        new DocgenTemplateManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.docgen =
        new DocgenManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.hubs =
        new HubsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.hubCollaborations =
        new HubCollaborationsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.hubItems =
        new HubItemsManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
    this.shieldLists =
        new ShieldListsManager.Builder()
            .auth(this.auth)
            .networkSession(this.networkSession)
            .build();
    this.archives =
        new ArchivesManager.Builder().auth(this.auth).networkSession(this.networkSession).build();
  }

  public FetchResponse makeRequest(FetchOptions fetchOptions) {
    Authentication auth = (fetchOptions.getAuth() == null ? this.auth : fetchOptions.getAuth());
    NetworkSession networkSession =
        (fetchOptions.getNetworkSession() == null
            ? this.networkSession
            : fetchOptions.getNetworkSession());
    FetchOptions enrichedFetchOptions =
        new FetchOptions.Builder(fetchOptions.getUrl(), fetchOptions.getMethod())
            .params(fetchOptions.getParams())
            .headers(fetchOptions.getHeaders())
            .data(fetchOptions.getData())
            .fileStream(fetchOptions.getFileStream())
            .multipartData(fetchOptions.getMultipartData())
            .contentType(fetchOptions.getContentType())
            .responseFormat(fetchOptions.getResponseFormat())
            .auth(auth)
            .networkSession(networkSession)
            .followRedirects(fetchOptions.getFollowRedirects())
            .build();
    return networkSession.getNetworkClient().fetch(enrichedFetchOptions);
  }

  public BoxClient withAsUserHeader(String userId) {
    return new BoxClient.Builder(this.auth)
        .networkSession(
            this.networkSession.withAdditionalHeaders(mapOf(entryOf("As-User", userId))))
        .build();
  }

  public BoxClient withSuppressedNotifications() {
    return new BoxClient.Builder(this.auth)
        .networkSession(
            this.networkSession.withAdditionalHeaders(mapOf(entryOf("Box-Notifications", "off"))))
        .build();
  }

  public BoxClient withExtraHeaders() {
    return withExtraHeaders(mapOf());
  }

  public BoxClient withExtraHeaders(Map<String, String> extraHeaders) {
    return new BoxClient.Builder(this.auth)
        .networkSession(this.networkSession.withAdditionalHeaders(extraHeaders))
        .build();
  }

  public BoxClient withCustomBaseUrls(BaseUrls baseUrls) {
    return new BoxClient.Builder(this.auth)
        .networkSession(this.networkSession.withCustomBaseUrls(baseUrls))
        .build();
  }

  public BoxClient withInterceptors(List<Interceptor> interceptors) {
    return new BoxClient.Builder(this.auth)
        .networkSession(this.networkSession.withInterceptors(interceptors))
        .build();
  }

  public Authentication getAuth() {
    return auth;
  }

  public NetworkSession getNetworkSession() {
    return networkSession;
  }

  public AuthorizationManager getAuthorization() {
    return authorization;
  }

  public FilesManager getFiles() {
    return files;
  }

  public TrashedFilesManager getTrashedFiles() {
    return trashedFiles;
  }

  public AppItemAssociationsManager getAppItemAssociations() {
    return appItemAssociations;
  }

  public DownloadsManager getDownloads() {
    return downloads;
  }

  public UploadsManager getUploads() {
    return uploads;
  }

  public ChunkedUploadsManager getChunkedUploads() {
    return chunkedUploads;
  }

  public ListCollaborationsManager getListCollaborations() {
    return listCollaborations;
  }

  public CommentsManager getComments() {
    return comments;
  }

  public TasksManager getTasks() {
    return tasks;
  }

  public FileVersionsManager getFileVersions() {
    return fileVersions;
  }

  public FileMetadataManager getFileMetadata() {
    return fileMetadata;
  }

  public FileClassificationsManager getFileClassifications() {
    return fileClassifications;
  }

  public SkillsManager getSkills() {
    return skills;
  }

  public FileWatermarksManager getFileWatermarks() {
    return fileWatermarks;
  }

  public FileRequestsManager getFileRequests() {
    return fileRequests;
  }

  public FoldersManager getFolders() {
    return folders;
  }

  public TrashedFoldersManager getTrashedFolders() {
    return trashedFolders;
  }

  public FolderMetadataManager getFolderMetadata() {
    return folderMetadata;
  }

  public FolderClassificationsManager getFolderClassifications() {
    return folderClassifications;
  }

  public TrashedItemsManager getTrashedItems() {
    return trashedItems;
  }

  public FolderWatermarksManager getFolderWatermarks() {
    return folderWatermarks;
  }

  public FolderLocksManager getFolderLocks() {
    return folderLocks;
  }

  public MetadataTemplatesManager getMetadataTemplates() {
    return metadataTemplates;
  }

  public ClassificationsManager getClassifications() {
    return classifications;
  }

  public MetadataCascadePoliciesManager getMetadataCascadePolicies() {
    return metadataCascadePolicies;
  }

  public SearchManager getSearch() {
    return search;
  }

  public UserCollaborationsManager getUserCollaborations() {
    return userCollaborations;
  }

  public TaskAssignmentsManager getTaskAssignments() {
    return taskAssignments;
  }

  public SharedLinksFilesManager getSharedLinksFiles() {
    return sharedLinksFiles;
  }

  public SharedLinksFoldersManager getSharedLinksFolders() {
    return sharedLinksFolders;
  }

  public WebLinksManager getWebLinks() {
    return webLinks;
  }

  public TrashedWebLinksManager getTrashedWebLinks() {
    return trashedWebLinks;
  }

  public SharedLinksWebLinksManager getSharedLinksWebLinks() {
    return sharedLinksWebLinks;
  }

  public SharedLinksAppItemsManager getSharedLinksAppItems() {
    return sharedLinksAppItems;
  }

  public UsersManager getUsers() {
    return users;
  }

  public SessionTerminationManager getSessionTermination() {
    return sessionTermination;
  }

  public AvatarsManager getAvatars() {
    return avatars;
  }

  public TransferManager getTransfer() {
    return transfer;
  }

  public EmailAliasesManager getEmailAliases() {
    return emailAliases;
  }

  public MembershipsManager getMemberships() {
    return memberships;
  }

  public InvitesManager getInvites() {
    return invites;
  }

  public GroupsManager getGroups() {
    return groups;
  }

  public WebhooksManager getWebhooks() {
    return webhooks;
  }

  public EventsManager getEvents() {
    return events;
  }

  public CollectionsManager getCollections() {
    return collections;
  }

  public RecentItemsManager getRecentItems() {
    return recentItems;
  }

  public RetentionPoliciesManager getRetentionPolicies() {
    return retentionPolicies;
  }

  public RetentionPolicyAssignmentsManager getRetentionPolicyAssignments() {
    return retentionPolicyAssignments;
  }

  public LegalHoldPoliciesManager getLegalHoldPolicies() {
    return legalHoldPolicies;
  }

  public LegalHoldPolicyAssignmentsManager getLegalHoldPolicyAssignments() {
    return legalHoldPolicyAssignments;
  }

  public FileVersionRetentionsManager getFileVersionRetentions() {
    return fileVersionRetentions;
  }

  public FileVersionLegalHoldsManager getFileVersionLegalHolds() {
    return fileVersionLegalHolds;
  }

  public ShieldInformationBarriersManager getShieldInformationBarriers() {
    return shieldInformationBarriers;
  }

  public ShieldInformationBarrierReportsManager getShieldInformationBarrierReports() {
    return shieldInformationBarrierReports;
  }

  public ShieldInformationBarrierSegmentsManager getShieldInformationBarrierSegments() {
    return shieldInformationBarrierSegments;
  }

  public ShieldInformationBarrierSegmentMembersManager getShieldInformationBarrierSegmentMembers() {
    return shieldInformationBarrierSegmentMembers;
  }

  public ShieldInformationBarrierSegmentRestrictionsManager
      getShieldInformationBarrierSegmentRestrictions() {
    return shieldInformationBarrierSegmentRestrictions;
  }

  public DevicePinnersManager getDevicePinners() {
    return devicePinners;
  }

  public TermsOfServicesManager getTermsOfServices() {
    return termsOfServices;
  }

  public TermsOfServiceUserStatusesManager getTermsOfServiceUserStatuses() {
    return termsOfServiceUserStatuses;
  }

  public CollaborationAllowlistEntriesManager getCollaborationAllowlistEntries() {
    return collaborationAllowlistEntries;
  }

  public CollaborationAllowlistExemptTargetsManager getCollaborationAllowlistExemptTargets() {
    return collaborationAllowlistExemptTargets;
  }

  public StoragePoliciesManager getStoragePolicies() {
    return storagePolicies;
  }

  public StoragePolicyAssignmentsManager getStoragePolicyAssignments() {
    return storagePolicyAssignments;
  }

  public ZipDownloadsManager getZipDownloads() {
    return zipDownloads;
  }

  public SignRequestsManager getSignRequests() {
    return signRequests;
  }

  public WorkflowsManager getWorkflows() {
    return workflows;
  }

  public SignTemplatesManager getSignTemplates() {
    return signTemplates;
  }

  public IntegrationMappingsManager getIntegrationMappings() {
    return integrationMappings;
  }

  public AiManager getAi() {
    return ai;
  }

  public AiStudioManager getAiStudio() {
    return aiStudio;
  }

  public DocgenTemplateManager getDocgenTemplate() {
    return docgenTemplate;
  }

  public DocgenManager getDocgen() {
    return docgen;
  }

  public HubsManager getHubs() {
    return hubs;
  }

  public HubCollaborationsManager getHubCollaborations() {
    return hubCollaborations;
  }

  public HubItemsManager getHubItems() {
    return hubItems;
  }

  public ShieldListsManager getShieldLists() {
    return shieldLists;
  }

  public ArchivesManager getArchives() {
    return archives;
  }

  public static class Builder {

    protected final Authentication auth;

    protected NetworkSession networkSession;

    public Builder(Authentication auth) {
      this.auth = auth;
      this.networkSession = new NetworkSession.Builder().baseUrls(new BaseUrls()).build();
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public BoxClient build() {
      return new BoxClient(this);
    }
  }
}
