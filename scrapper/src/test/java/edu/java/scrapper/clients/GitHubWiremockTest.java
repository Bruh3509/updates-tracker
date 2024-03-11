package edu.java.scrapper.clients;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import edu.java.scrapper.configuration.ClientConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ClientConfig.class)
public class GitHubWiremockTest {
    @Autowired
    @Qualifier("githubLocalhost")
    public GitHubClient gitHubClient;

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void startWireMock() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        wireMockServer.stubFor(WireMock.get("/repos/Bruh3509/tinkoff")
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody("""
                    {
                    	"id": 700400107,
                    	"node_id": "R_kgDOKb9B6w",
                    	"name": "tinkoff",
                    	"full_name": "Bruh3509/tinkoff",
                    	"private": false,
                    	"owner": {
                    		"login": "Bruh3509",
                    		"id": 69002959,
                    		"node_id": "MDQ6VXNlcjY5MDAyOTU5",
                    		"avatar_url": "https://avatars.githubusercontent.com/u/69002959?v=4",
                    		"gravatar_id": "",
                    		"url": "https://api.github.com/users/Bruh3509",
                    		"html_url": "https://github.com/Bruh3509",
                    		"followers_url": "https://api.github.com/users/Bruh3509/followers",
                    		"following_url": "https://api.github.com/users/Bruh3509/following{/other_user}",
                    		"gists_url": "https://api.github.com/users/Bruh3509/gists{/gist_id}",
                    		"starred_url": "https://api.github.com/users/Bruh3509/starred{/owner}{/repo}",
                    		"subscriptions_url": "https://api.github.com/users/Bruh3509/subscriptions",
                    		"organizations_url": "https://api.github.com/users/Bruh3509/orgs",
                    		"repos_url": "https://api.github.com/users/Bruh3509/repos",
                    		"events_url": "https://api.github.com/users/Bruh3509/events{/privacy}",
                    		"received_events_url": "https://api.github.com/users/Bruh3509/received_events",
                    		"type": "User",
                    		"site_admin": false
                    	},
                    	"html_url": "https://github.com/Bruh3509/tinkoff",
                    	"description": null,
                    	"fork": false,
                    	"url": "https://api.github.com/repos/Bruh3509/tinkoff",
                    	"forks_url": "https://api.github.com/repos/Bruh3509/tinkoff/forks",
                    	"keys_url": "https://api.github.com/repos/Bruh3509/tinkoff/keys{/key_id}",
                    	"collaborators_url": "https://api.github.com/repos/Bruh3509/tinkoff/collaborators{/collaborator}",
                    	"teams_url": "https://api.github.com/repos/Bruh3509/tinkoff/teams",
                    	"hooks_url": "https://api.github.com/repos/Bruh3509/tinkoff/hooks",
                    	"issue_events_url": "https://api.github.com/repos/Bruh3509/tinkoff/issues/events{/number}",
                    	"events_url": "https://api.github.com/repos/Bruh3509/tinkoff/events",
                    	"assignees_url": "https://api.github.com/repos/Bruh3509/tinkoff/assignees{/user}",
                    	"branches_url": "https://api.github.com/repos/Bruh3509/tinkoff/branches{/branch}",
                    	"tags_url": "https://api.github.com/repos/Bruh3509/tinkoff/tags",
                    	"blobs_url": "https://api.github.com/repos/Bruh3509/tinkoff/git/blobs{/sha}",
                    	"git_tags_url": "https://api.github.com/repos/Bruh3509/tinkoff/git/tags{/sha}",
                    	"git_refs_url": "https://api.github.com/repos/Bruh3509/tinkoff/git/refs{/sha}",
                    	"trees_url": "https://api.github.com/repos/Bruh3509/tinkoff/git/trees{/sha}",
                    	"statuses_url": "https://api.github.com/repos/Bruh3509/tinkoff/statuses/{sha}",
                    	"languages_url": "https://api.github.com/repos/Bruh3509/tinkoff/languages",
                    	"stargazers_url": "https://api.github.com/repos/Bruh3509/tinkoff/stargazers",
                    	"contributors_url": "https://api.github.com/repos/Bruh3509/tinkoff/contributors",
                    	"subscribers_url": "https://api.github.com/repos/Bruh3509/tinkoff/subscribers",
                    	"subscription_url": "https://api.github.com/repos/Bruh3509/tinkoff/subscription",
                    	"commits_url": "https://api.github.com/repos/Bruh3509/tinkoff/commits{/sha}",
                    	"git_commits_url": "https://api.github.com/repos/Bruh3509/tinkoff/git/commits{/sha}",
                    	"comments_url": "https://api.github.com/repos/Bruh3509/tinkoff/comments{/number}",
                    	"issue_comment_url": "https://api.github.com/repos/Bruh3509/tinkoff/issues/comments{/number}",
                    	"contents_url": "https://api.github.com/repos/Bruh3509/tinkoff/contents/{+path}",
                    	"compare_url": "https://api.github.com/repos/Bruh3509/tinkoff/compare/{base}...{head}",
                    	"merges_url": "https://api.github.com/repos/Bruh3509/tinkoff/merges",
                    	"archive_url": "https://api.github.com/repos/Bruh3509/tinkoff/{archive_format}{/ref}",
                    	"downloads_url": "https://api.github.com/repos/Bruh3509/tinkoff/downloads",
                    	"issues_url": "https://api.github.com/repos/Bruh3509/tinkoff/issues{/number}",
                    	"pulls_url": "https://api.github.com/repos/Bruh3509/tinkoff/pulls{/number}",
                    	"milestones_url": "https://api.github.com/repos/Bruh3509/tinkoff/milestones{/number}",
                    	"notifications_url": "https://api.github.com/repos/Bruh3509/tinkoff/notifications{?since,all,participating}",
                    	"labels_url": "https://api.github.com/repos/Bruh3509/tinkoff/labels{/name}",
                    	"releases_url": "https://api.github.com/repos/Bruh3509/tinkoff/releases{/id}",
                    	"deployments_url": "https://api.github.com/repos/Bruh3509/tinkoff/deployments",
                    	"created_at": "2023-10-04T14:23:26Z",
                    	"updated_at": "2023-10-15T11:25:02Z",
                    	"pushed_at": "2024-01-13T16:23:35Z",
                    	"git_url": "git://github.com/Bruh3509/tinkoff.git",
                    	"ssh_url": "git@github.com:Bruh3509/tinkoff.git",
                    	"clone_url": "https://github.com/Bruh3509/tinkoff.git",
                    	"svn_url": "https://github.com/Bruh3509/tinkoff",
                    	"homepage": null,
                    	"size": 8605,
                    	"stargazers_count": 0,
                    	"watchers_count": 0,
                    	"language": "Java",
                    	"has_issues": true,
                    	"has_projects": true,
                    	"has_downloads": true,
                    	"has_wiki": true,
                    	"has_pages": false,
                    	"has_discussions": false,
                    	"forks_count": 0,
                    	"mirror_url": null,
                    	"archived": false,
                    	"disabled": false,
                    	"open_issues_count": 0,
                    	"license": null,
                    	"allow_forking": true,
                    	"is_template": false,
                    	"web_commit_signoff_required": false,
                    	"topics": [],
                    	"visibility": "public",
                    	"forks": 0,
                    	"open_issues": 0,
                    	"watchers": 0,
                    	"default_branch": "main",
                    	"temp_clone_token": null,
                    	"network_count": 0,
                    	"subscribers_count": 1
                    }
                      """)
            ));
    }

    @AfterAll
    static void stopWireMock() {
        wireMockServer.stop();
    }

    @Test
    void testWireMock() {
        var response = gitHubClient.getRepository("Bruh3509", "tinkoff").getBody();
        assertTrue(wireMockServer.isRunning());
        assertThat(response.link()).isEqualTo("https://github.com/Bruh3509/tinkoff");
        assertThat(response.name()).isEqualTo("Bruh3509/tinkoff");
        assertThat(response.language()).isEqualTo("Java");
    }
}
