package eu.epitech.t_dev_700.utils;

import eu.epitech.t_dev_700.entities.TeamEntity;
import eu.epitech.t_dev_700.mappers.TeamMapper;
import eu.epitech.t_dev_700.models.TeamModels;
import eu.epitech.t_dev_700.repositories.TeamRepository;
import eu.epitech.t_dev_700.services.CRUDService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CRUDHookUtilsTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    private TestCRUDService testService;
    private TeamEntity testEntity;
    private TeamModels.PostTeamRequest postRequest;

    @BeforeEach
    void setUp() {
        testService = new TestCRUDService(teamRepository, teamMapper);
        testEntity = new TeamEntity();
        testEntity.setId(1L);
        testEntity.setName("Test Team");

        postRequest = new TeamModels.PostTeamRequest("Test Team", "Description");
    }

    @Test
    void testBeforeCreate_shouldInvokeHook() {
        CRUDHookUtils.beforeCreate(testService, testEntity, postRequest);

        assertThat(testService.beforeCreateCalled).isTrue();
    }

    @Test
    void testAfterCreate_shouldInvokeHook() {
        CRUDHookUtils.afterCreate(testService, testEntity, postRequest);

        assertThat(testService.afterCreateCalled).isTrue();
    }

    @Test
    void testBeforeUpdate_shouldInvokeHook() {
        CRUDHookUtils.beforeUpdate(testService, testEntity, postRequest);

        assertThat(testService.beforeUpdateCalled).isTrue();
    }

    @Test
    void testAfterUpdate_shouldInvokeHook() {
        CRUDHookUtils.afterUpdate(testService, testEntity, postRequest);

        assertThat(testService.afterUpdateCalled).isTrue();
    }

    @Test
    void testBeforeReplace_shouldInvokeHook() {
        CRUDHookUtils.beforeReplace(testService, testEntity, postRequest);

        assertThat(testService.beforeReplaceCalled).isTrue();
    }

    @Test
    void testAfterReplace_shouldInvokeHook() {
        CRUDHookUtils.afterReplace(testService, testEntity, postRequest);

        assertThat(testService.afterReplaceCalled).isTrue();
    }

    @Test
    void testBeforeDelete_shouldInvokeHook() {
        CRUDHookUtils.beforeDelete(testService, testEntity);

        assertThat(testService.beforeDeleteCalled).isTrue();
    }

    @Test
    void testAfterDelete_shouldInvokeHook() {
        CRUDHookUtils.afterDelete(testService, testEntity);

        assertThat(testService.afterDeleteCalled).isTrue();
    }

    @Test
    void testHookMoment_enum() {
        CRUDHookUtils.Moment[] moments = CRUDHookUtils.Moment.values();
        assertThat(moments).hasSize(2);
        assertThat(moments).containsExactlyInAnyOrder(
                CRUDHookUtils.Moment.BEFORE,
                CRUDHookUtils.Moment.AFTER
        );
    }

    @Test
    void testHookAction_enum() {
        CRUDHookUtils.Action[] actions = CRUDHookUtils.Action.values();
        assertThat(actions).hasSize(4);
        assertThat(actions).containsExactlyInAnyOrder(
                CRUDHookUtils.Action.CREATE,
                CRUDHookUtils.Action.UPDATE,
                CRUDHookUtils.Action.REPLACE,
                CRUDHookUtils.Action.DELETE
        );
    }

    @Test
    void testMultipleHooks_shouldAllBeInvoked() {
        TestServiceWithMultipleHooks service = new TestServiceWithMultipleHooks(teamRepository, teamMapper);

        CRUDHookUtils.beforeCreate(service, testEntity, postRequest);

        assertThat(service.hook1Called).isTrue();
        assertThat(service.hook2Called).isTrue();
    }

    @Test
    void testHook_withOnlyEntityParameter() {
        CRUDHookUtils.beforeDelete(testService, testEntity);

        assertThat(testService.beforeDeleteCalled).isTrue();
        assertThat(testService.entityReceived).isEqualTo(testEntity);
    }

    @Test
    void testHook_withEntityAndRequestParameters() {
        CRUDHookUtils.beforeCreate(testService, testEntity, postRequest);

        assertThat(testService.beforeCreateCalled).isTrue();
        assertThat(testService.entityReceived).isEqualTo(testEntity);
        assertThat(testService.requestReceived).isEqualTo(postRequest);
    }

    // Test service with hooks
    static class TestCRUDService extends CRUDService<TeamEntity, TeamModels.TeamModel, TeamModels.PostTeamRequest, TeamModels.PutTeamRequest, TeamModels.PatchTeamRequest> {

        boolean beforeCreateCalled = false;
        boolean afterCreateCalled = false;
        boolean beforeUpdateCalled = false;
        boolean afterUpdateCalled = false;
        boolean beforeReplaceCalled = false;
        boolean afterReplaceCalled = false;
        boolean beforeDeleteCalled = false;
        boolean afterDeleteCalled = false;
        TeamEntity entityReceived = null;
        Object requestReceived = null;

        protected TestCRUDService(TeamRepository repository, TeamMapper mapper) {
            super(repository, mapper, "Test");
        }

        @CRUDHookUtils.CRUDHook(moment = CRUDHookUtils.Moment.BEFORE, action = CRUDHookUtils.Action.CREATE)
        public void beforeCreate(TeamEntity entity, TeamModels.PostTeamRequest request) {
            beforeCreateCalled = true;
            entityReceived = entity;
            requestReceived = request;
        }

        @CRUDHookUtils.CRUDHook(moment = CRUDHookUtils.Moment.AFTER, action = CRUDHookUtils.Action.CREATE)
        public void afterCreate(TeamEntity entity, TeamModels.PostTeamRequest request) {
            afterCreateCalled = true;
        }

        @CRUDHookUtils.CRUDHook(moment = CRUDHookUtils.Moment.BEFORE, action = CRUDHookUtils.Action.UPDATE)
        public void beforeUpdate(TeamEntity entity, TeamModels.PostTeamRequest request) {
            beforeUpdateCalled = true;
        }

        @CRUDHookUtils.CRUDHook(moment = CRUDHookUtils.Moment.AFTER, action = CRUDHookUtils.Action.UPDATE)
        public void afterUpdate(TeamEntity entity, TeamModels.PostTeamRequest request) {
            afterUpdateCalled = true;
        }

        @CRUDHookUtils.CRUDHook(moment = CRUDHookUtils.Moment.BEFORE, action = CRUDHookUtils.Action.REPLACE)
        public void beforeReplace(TeamEntity entity, TeamModels.PostTeamRequest request) {
            beforeReplaceCalled = true;
        }

        @CRUDHookUtils.CRUDHook(moment = CRUDHookUtils.Moment.AFTER, action = CRUDHookUtils.Action.REPLACE)
        public void afterReplace(TeamEntity entity, TeamModels.PostTeamRequest request) {
            afterReplaceCalled = true;
        }

        @CRUDHookUtils.CRUDHook(moment = CRUDHookUtils.Moment.BEFORE, action = CRUDHookUtils.Action.DELETE)
        public void beforeDelete(TeamEntity entity) {
            beforeDeleteCalled = true;
            entityReceived = entity;
        }

        @CRUDHookUtils.CRUDHook(moment = CRUDHookUtils.Moment.AFTER, action = CRUDHookUtils.Action.DELETE)
        public void afterDelete(TeamEntity entity) {
            afterDeleteCalled = true;
        }
    }

    // Test service with multiple hooks for the same action
    static class TestServiceWithMultipleHooks extends CRUDService<TeamEntity, TeamModels.TeamModel, TeamModels.PostTeamRequest, TeamModels.PutTeamRequest, TeamModels.PatchTeamRequest> {

        boolean hook1Called = false;
        boolean hook2Called = false;

        protected TestServiceWithMultipleHooks(TeamRepository repository, TeamMapper mapper) {
            super(repository, mapper, "Test");
        }

        @CRUDHookUtils.CRUDHook(moment = CRUDHookUtils.Moment.BEFORE, action = CRUDHookUtils.Action.CREATE)
        public void hook1(TeamEntity entity, TeamModels.PostTeamRequest request) {
            hook1Called = true;
        }

        @CRUDHookUtils.CRUDHook(moment = CRUDHookUtils.Moment.BEFORE, action = CRUDHookUtils.Action.CREATE)
        public void hook2(TeamEntity entity, TeamModels.PostTeamRequest request) {
            hook2Called = true;
        }
    }
}
