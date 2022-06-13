package com.example.myapplication.network

import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @POST("/users/auth/signup")
    fun signUp(
        @Body signUp: SignUp
    ):Call<SignUpResult>

    @POST("/users/auth/signin")
    fun signIn(
        @Body signIn: SignIn
    ):Call<SignInResult>

    @GET("/users")
    fun users(
    ):Call<UsersResult>

    @GET("/users/backlog")
    fun backlog(
        @Header("X-AUTH-TOKEN") accessToken: String?
    ):Call<BacklogResult>

    @POST("/projects")
    fun projects(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Body projects: Projects
    ):Call<ProjectsResult>

    @POST("/projects/member")
    fun member(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Body member: Member
    ):Call<MemberResult>

    @POST("/sprints")
    fun createSprint(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Body createSprint: CreateSprint
    ):Call<CreateSprintResult>

    @GET("/sprints/project/{projectId}")
    fun inquirySprints(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Path("projectId") projectId: Int?
    ):Call<InquireSprintsResult>

    @GET("/projects/{projectId}/members")
    fun inquiryMembers(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Path("projectId") projectId: Int
    ):Call<InquiryMembersResult>

    @GET("/sprints/{sprintId}/members")
    fun sprintMembers(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Path("sprintId") sprintId: Int?
    ):Call<sprintMembersResult>

    @DELETE("/sprints/{sprintId}")
    fun sprintDelete(
        @Header("X-AUTH-TOKEN") accessToken: String?,
        @Path("sprintId") sprintId: Int?
    ):Call<SprintDeleteResult>
}