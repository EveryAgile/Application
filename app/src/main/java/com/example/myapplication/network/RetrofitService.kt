package com.example.myapplication.network

import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @POST("users/auth/reissue")
    fun reIssue(
        @Body tokenRequestDto: TokenRequestDto
    ):Call<ProjectCreate>

    @POST("users/auth/signin")
    fun signIn(
        @Body signInRequestDto: SignInRequestDto
    ):Call<CommenResult>

    @POST("/users/auth/signup")
    fun signUp(
        @Body signUpRequestDto: SignUpRequestDto
    ):Call<CommonResult>


    @GET("users/backlog")
    fun getBacklog(
        @Header("X-AUTH-TOKEN") accessToken: String?
    ):Call<CommenResult>

    @GET("users/project")
    fun getProject(
        @Header("X-AUTH-TOKEN") accessToken: String?
    ):Call<ProjectCreate>

    @GET("users/task")
    fun getTask(
        @Header("X-AUTH-TOKEN") accessToken: String?
    ):Call<ProjectCreate>

    @GET("/users")
    fun getUsers(): Call<CommenResult>

    @POST("/projects")
    fun postProject(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Body projectRequestDto: ProjectRequestDto
    ):Call<ProjectCreate>

    @POST("/sprints")
    fun postProduct(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Body sprintRequestDto: SprintRequestDto
    ):Call<SprintCreate>

    @GET("/users/sprint")
    fun getProduct(
        @Header("X-AUTH-TOKEN") accessToken: String?,
    ):Call<SprintCreate>

    @GET("/sprints/{sprintId}")
    fun getProductContent(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Path("sprintId") sprintId: Long
    ):Call<SprintContent>

    @POST("/backlogs")
    fun postSprint(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Body backlogRequestDto: BacklogRequestDto
    ):Call<CreateBacklog>

    @POST("/projects/member")
    fun postMember(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Body inviteRequestDto: InviteRequestDto
    ):Call<CommonResult>

    @GET("/sprints/project/{projectId}")
    fun getProductList(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Path("projectId")  projectId: Long
    ):Call<SprintList>

    @DELETE("/projects/{projectId}")
    fun deleteProject(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Path("projectId")  projectId: Long
    ):Call<CommonResult>

    @GET("backlogs/sprint/{sprintId}")
    fun getSprintList(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Path("sprintId")  sprintId: Long
    ):Call<BacklogList>

    @GET("backlogs/{backlogId}")
    fun getSprint(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Path("backlogId")  backlogId: Long
    ):Call<BacklogList>
}