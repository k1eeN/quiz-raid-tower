package dev.k1een.qrt.feature.game.impl.presentation.setup.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.k1een.qrt.core.designsystem.components.QrtErrorScreen
import dev.k1een.qrt.core.designsystem.components.QrtIcon
import dev.k1een.qrt.core.designsystem.components.QrtIconImage
import dev.k1een.qrt.core.designsystem.components.QrtLoadingScreen
import dev.k1een.qrt.core.designsystem.components.QrtPrimaryButton
import dev.k1een.qrt.core.designsystem.components.QrtScreen
import dev.k1een.qrt.core.designsystem.components.QrtSelectionTile
import dev.k1een.qrt.core.designsystem.components.QrtWordmark
import dev.k1een.qrt.core.designsystem.components.ReadingScrim
import dev.k1een.qrt.core.designsystem.theme.QrtColors
import dev.k1een.qrt.core.designsystem.theme.QrtSizes
import dev.k1een.qrt.core.designsystem.theme.QrtTheme
import dev.k1een.qrt.feature.game.api.models.Category
import dev.k1een.qrt.feature.game.impl.presentation.setup.mappers.SetupRaidUiMapper
import dev.k1een.qrt.feature.game.impl.presentation.setup.models.SetupRaidAction
import dev.k1een.qrt.feature.game.impl.presentation.setup.models.SetupRaidState
import dev.k1een.qrt.feature.game.impl.presentation.setup.models.SetupRaidUiState
import dev.k1een.qrt.feature.game.impl.resources.Res
import dev.k1een.qrt.feature.game.impl.resources.setup_back
import dev.k1een.qrt.feature.game.impl.resources.setup_category
import dev.k1een.qrt.feature.game.impl.resources.setup_difficulty
import dev.k1een.qrt.feature.game.impl.resources.setup_start
import dev.k1een.qrt.feature.game.impl.resources.setup_subtitle
import dev.k1een.qrt.feature.game.impl.resources.setup_summary
import dev.k1een.qrt.feature.game.impl.resources.setup_title
import org.jetbrains.compose.resources.stringResource

private const val GRID_COLUMN_COUNT = 3
private const val HEADER_KEY = "header"
private const val ALL_TOPICS_KEY = "all_topics"
private const val DIFFICULTY_KEY = "difficulty"
private const val START_KEY = "start"
private const val CATEGORY_CONTENT_TYPE = "category"
private const val DIFFICULTY_CONTENT_TYPE = "difficulty"
private val CategoryTileMinHeight = 108.dp
private val DifficultyTileMinHeight = 72.dp

@Composable
internal fun SetupRaidScreen(
    state: SetupRaidUiState,
    onAction: (SetupRaidAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (val screenState = state.state) {
        SetupRaidUiState.State.Loading -> QrtLoadingScreen(modifier = modifier)
        is SetupRaidUiState.State.Error -> QrtErrorScreen(
            onRetry = { onAction(SetupRaidAction.RetryClicked) },
            onHome = { onAction(SetupRaidAction.HomeClicked) },
            modifier = modifier,
            title = stringResource(screenState.title),
            description = stringResource(screenState.description),
        )
        is SetupRaidUiState.State.Content -> SetupRaidContent(
            state = screenState,
            onAction = onAction,
            modifier = modifier,
        )
    }
}

@Composable
private fun SetupRaidContent(
    state: SetupRaidUiState.State.Content,
    onAction: (SetupRaidAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val safeTop = WindowInsets.safeDrawing.asPaddingValues().calculateTopPadding()
    val backLabel = stringResource(Res.string.setup_back)

    QrtScreen(background = state.background, modifier = modifier) {
        ReadingScrim(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 215.dp),
        ) {}
        LazyVerticalGrid(
            columns = GridCells.Fixed(GRID_COLUMN_COUNT),
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 32.dp),
            horizontalArrangement = Arrangement.spacedBy(10.5.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item(key = HEADER_KEY, span = { GridItemSpan(maxLineSpan) }) {
                Column {
                    Spacer(modifier = Modifier.height(maxOf(45.dp - safeTop, 0.dp)))
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .size(QrtSizes.MinTouchTarget)
                                .semantics { contentDescription = backLabel }
                                .clickable(role = Role.Button) { onAction(SetupRaidAction.BackClicked) },
                            contentAlignment = Alignment.CenterStart,
                        ) {
                            QrtIconImage(
                                icon = QrtIcon.Back,
                                contentDescription = null,
                                modifier = Modifier.size(QrtSizes.NavigationIcon),
                            )
                        }
                        QrtWordmark(
                            compact = true,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .width(QrtSizes.CompactWordmarkWidth)
                                .height(QrtSizes.CompactWordmarkHeight),
                        )
                    }
                    Spacer(modifier = Modifier.height(123.dp))
                    Text(
                        text = stringResource(Res.string.setup_title),
                        style = MaterialTheme.typography.displayMedium.copy(
                            fontSize = 34.sp,
                            lineHeight = 42.sp,
                            fontWeight = FontWeight.SemiBold,
                        ),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(Res.string.setup_subtitle),
                        style = MaterialTheme.typography.bodyLarge,
                        color = QrtColors.Muted,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    SetupHeading(stringResource(Res.string.setup_category))
                }
            }
            item(key = ALL_TOPICS_KEY, contentType = CATEGORY_CONTENT_TYPE) {
                QrtSelectionTile(
                    text = stringResource(state.allTopics.title),
                    icon = state.allTopics.icon,
                    selected = state.allTopics.isSelected,
                    onClick = { onAction(SetupRaidAction.CategoryClicked(null)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = CategoryTileMinHeight),
                )
            }
            items(
                items = state.categories,
                key = { category -> category.id },
                contentType = { CATEGORY_CONTENT_TYPE },
            ) { category ->
                QrtSelectionTile(
                    text = category.name,
                    icon = category.icon,
                    selected = category.isSelected,
                    onClick = { onAction(SetupRaidAction.CategoryClicked(category.id)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = CategoryTileMinHeight),
                )
            }
            item(key = DIFFICULTY_KEY, span = { GridItemSpan(maxLineSpan) }) {
                SetupHeading(
                    text = stringResource(Res.string.setup_difficulty),
                    modifier = Modifier.padding(top = 16.dp),
                )
            }
            items(
                items = state.difficulties,
                key = { difficultyUiModel -> difficultyUiModel.difficulty },
                contentType = { DIFFICULTY_CONTENT_TYPE },
            ) { difficultyUiModel ->
                QrtSelectionTile(
                    text = stringResource(difficultyUiModel.title),
                    icon = difficultyUiModel.icon,
                    selected = difficultyUiModel.isSelected,
                    onClick = { onAction(SetupRaidAction.DifficultyClicked(difficultyUiModel.difficulty)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = DifficultyTileMinHeight),
                    accent = difficultyUiModel.color,
                )
            }
            item(key = START_KEY, span = { GridItemSpan(maxLineSpan) }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(Res.string.setup_summary),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 24.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = QrtColors.Muted,
                        textAlign = TextAlign.Center,
                    )
                    QrtPrimaryButton(
                        text = stringResource(Res.string.setup_start),
                        onClick = { onAction(SetupRaidAction.StartRaidClicked) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 64.dp),
                        trailingArrow = true,
                    )
                }
            }
        }
    }
}

@Composable
private fun SetupHeading(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
    )
}

@Preview(name = "Setup raid", widthDp = 393, heightDp = 852)
@Preview(name = "Setup raid · 360 dp", widthDp = 360, heightDp = 800)
@Composable
private fun SetupRaidPreview() {
    QrtTheme {
        SetupRaidScreen(
            state = SetupRaidUiMapper()(
                SetupRaidState(
                    state = SetupRaidState.State.Content(categories = setupRaidPreviewCategories()),
                ),
            ),
            onAction = {},
        )
    }
}

@Preview(name = "Setup raid · loading", widthDp = 393, heightDp = 852)
@Composable
private fun SetupRaidLoadingPreview() {
    QrtTheme {
        SetupRaidScreen(SetupRaidUiMapper()(SetupRaidState()), onAction = {})
    }
}

@Preview(name = "Setup raid · error", widthDp = 393, heightDp = 852)
@Composable
private fun SetupRaidErrorPreview() {
    QrtTheme {
        SetupRaidScreen(
            state = SetupRaidUiMapper()(SetupRaidState(state = SetupRaidState.State.Error)),
            onAction = {},
        )
    }
}

private fun setupRaidPreviewCategories() = listOf(
    Category(9, "General Knowledge"),
    Category(10, "Entertainment: Books"),
    Category(11, "Entertainment: Film"),
    Category(12, "Entertainment: Music"),
    Category(13, "Entertainment: Musicals & Theatres"),
    Category(14, "Entertainment: Television"),
    Category(15, "Entertainment: Video Games"),
    Category(16, "Entertainment: Board Games"),
    Category(17, "Science & Nature"),
    Category(18, "Science: Computers"),
    Category(19, "Science: Mathematics"),
    Category(20, "Mythology"),
    Category(21, "Sports"),
    Category(22, "Geography"),
    Category(23, "History"),
    Category(24, "Politics"),
    Category(25, "Art"),
    Category(26, "Celebrities"),
    Category(27, "Animals"),
    Category(28, "Vehicles"),
    Category(29, "Entertainment: Comics"),
    Category(30, "Science: Gadgets"),
    Category(31, "Entertainment: Japanese Anime & Manga"),
    Category(32, "Entertainment: Cartoon & Animations"),
)
