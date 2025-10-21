package com.dak0ta.learnity.core.database.data.repository

import androidx.room.withTransaction
import com.dak0ta.learnity.core.database.data.dao.StudentDao
import com.dak0ta.learnity.core.database.data.dao.TutorDao
import com.dak0ta.learnity.core.database.data.dao.UserDao
import com.dak0ta.learnity.core.database.data.db.AppDatabase
import com.dak0ta.learnity.core.database.data.mapper.toDomain
import com.dak0ta.learnity.core.database.data.mapper.toEntity
import com.dak0ta.learnity.core.database.domain.repository.UserWithRoleLocalRepository
import com.dak0ta.learnity.core.domain.user.UserWithRole
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Singleton

// TODO detekt - TooManyFunctions
@Suppress("TooManyFunctions")
@Singleton
internal class UserWithRoleLocalRepositoryImpl(
    private val db: AppDatabase,
    private val userDao: UserDao,
    private val studentDao: StudentDao,
    private val tutorDao: TutorDao,
) : UserWithRoleLocalRepository {

    override fun observeUser(id: String): Flow<UserWithRole?> {
        val userFlow = userDao.getByIdFlow(id)
        val studentFlow = studentDao.getByIdFlow(id)
        val tutorFlow = tutorDao.getByIdFlow(id)

        return combine(userFlow, studentFlow, tutorFlow) { user, student, tutor ->
            user?.let { UserWithRole(it.toDomain(), student?.toDomain(), tutor?.toDomain()) }
        }
    }

    override fun observeStudents(): Flow<List<UserWithRole>> {
        val usersFlow = userDao.getAllFlow()
        val studentsFlow = studentDao.getAllFlow()

        return combine(usersFlow, studentsFlow) { users, students ->
            val studentMap = students.associateBy { it.id }

            users.mapNotNull { user ->
                studentMap[user.id]?.let { student ->
                    UserWithRole(
                        user = user.toDomain(),
                        student = student.toDomain(),
                        tutor = null,
                    )
                }
            }
        }
    }

    override fun observeTutors(): Flow<List<UserWithRole>> {
        val usersFlow = userDao.getAllFlow()
        val tutorsFlow = tutorDao.getAllFlow()

        return combine(usersFlow, tutorsFlow) { users, tutors ->
            val tutorMap = tutors.associateBy { it.id }

            users.mapNotNull { user ->
                tutorMap[user.id]?.let { tutor ->
                    UserWithRole(
                        user = user.toDomain(),
                        student = null,
                        tutor = tutor.toDomain(),
                    )
                }
            }
        }
    }

    override fun observeUsers(): Flow<List<UserWithRole>> {
        val usersFlow = userDao.getAllFlow()
        val studentsFlow = studentDao.getAllFlow()
        val tutorsFlow = tutorDao.getAllFlow()

        return combine(usersFlow, studentsFlow, tutorsFlow) { users, students, tutors ->
            val studentMap = students.associateBy { it.id }
            val tutorMap = tutors.associateBy { it.id }

            users.map { user ->
                UserWithRole(
                    user = user.toDomain(),
                    student = studentMap[user.id]?.toDomain(),
                    tutor = tutorMap[user.id]?.toDomain(),
                )
            }
        }
    }

    override suspend fun getStudents(): List<UserWithRole> {
        val users = userDao.getAllOnce()
        val students = studentDao.getAllOnce().associateBy { it.id }

        return users.mapNotNull { user ->
            students[user.id]?.let { student ->
                UserWithRole(
                    user = user.toDomain(),
                    student = student.toDomain(),
                    tutor = null,
                )
            }
        }
    }

    override suspend fun getTutors(): List<UserWithRole> {
        val users = userDao.getAllOnce()
        val tutors = tutorDao.getAllOnce().associateBy { it.id }

        return users.mapNotNull { user ->
            tutors[user.id]?.let { tutor ->
                UserWithRole(
                    user = user.toDomain(),
                    student = null,
                    tutor = tutor.toDomain(),
                )
            }
        }
    }

    override suspend fun getUsers(): List<UserWithRole> {
        val users = userDao.getAllOnce()
        val students = studentDao.getAllOnce().associateBy { it.id }
        val tutors = tutorDao.getAllOnce().associateBy { it.id }

        return users.map { user ->
            UserWithRole(
                user = user.toDomain(),
                student = students[user.id]?.toDomain(),
                tutor = tutors[user.id]?.toDomain(),
            )
        }
    }

    override suspend fun getUser(id: String): UserWithRole? {
        val user = userDao.getByIdOnce(id) ?: return null
        val student = studentDao.getByIdOnce(id)
        val tutor = tutorDao.getByIdOnce(id)

        return UserWithRole(user.toDomain(), student?.toDomain(), tutor?.toDomain())
    }

    override suspend fun upsertUser(userWithRole: UserWithRole) {
        db.withTransaction {
            userDao.insert(userWithRole.user.toEntity())
            userWithRole.student?.let { studentDao.insert(it.toEntity(userWithRole.user.id)) }
            userWithRole.tutor?.let { tutorDao.insert(it.toEntity(userWithRole.user.id)) }
        }
    }

    override suspend fun upsertUsers(usersWithRole: List<UserWithRole>) {
        db.withTransaction {
            userDao.insertAll(usersWithRole.map { it.user.toEntity() })
            studentDao.insertAll(usersWithRole.mapNotNull { it.student?.toEntity(it.user.id) })
            tutorDao.insertAll(usersWithRole.mapNotNull { it.tutor?.toEntity(it.user.id) })
        }
    }

    override suspend fun deleteUser(id: String) {
        db.withTransaction {
            userDao.deleteById(id)
        }
    }

    override suspend fun deleteUsers() {
        db.withTransaction {
            userDao.deleteAll()
        }
    }
}
