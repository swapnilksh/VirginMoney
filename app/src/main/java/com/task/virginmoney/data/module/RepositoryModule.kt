
import com.task.virginmoney.domain.repository.RepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {
        RepositoryImpl(get())
    }
}