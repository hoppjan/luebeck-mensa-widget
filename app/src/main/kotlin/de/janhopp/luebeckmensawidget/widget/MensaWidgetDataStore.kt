package de.janhopp.luebeckmensawidget.widget

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.glance.state.GlanceStateDefinition
import de.janhopp.luebeckmensawidget.api.MensaApi
import de.janhopp.luebeckmensawidget.storage.MenuStorage
import de.janhopp.luebeckmensawidget.storage.OptionsStorage
import de.janhopp.luebeckmensawidget.utils.syncToday
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

class MensaWidgetDataStore(context: Context) : DataStore<MensaWidgetState> {
    private val api: MensaApi = MensaApi()
    private val storage: MenuStorage = MenuStorage(context)
    private val config: OptionsStorage = OptionsStorage(context)

    override val data: Flow<MensaWidgetState>
        get() = flow {
            emit(
                MensaWidgetState(
                    config.getWidgetConfig(),
                    syncToday(api, config, storage),
                )
            )
        }

    override suspend fun updateData(transform: suspend (MensaWidgetState) -> MensaWidgetState): MensaWidgetState {
        throw NotImplementedError()
    }
}

class MensaWidgetStateDefinition : GlanceStateDefinition<MensaWidgetState> {
    override suspend fun getDataStore(
        context: Context,
        fileKey: String,
    ): DataStore<MensaWidgetState> {
        return MensaWidgetDataStore(context)
    }

    override fun getLocation(context: Context, fileKey: String): File {
        throw NotImplementedError()
    }
}
