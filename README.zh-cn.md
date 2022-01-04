# <img src="https://i.loli.net/2018/12/14/5c13bf0563d18.gif" alt="pacman logo" width="50"/> Pacman 吃豆人

[![](https://img.shields.io/github/license/Songkeys/pacman.svg)](https://github.com/Songkeys/pacman) [![](https://img.shields.io/badge/GitHub-pacman-blue.svg)](https://github.com/Songkeys/pacman) [![](https://img.shields.io/badge/GitLab-pacman-orange.svg)](https://projects.cs.nott.ac.uk/psysz4/pacman)

[English](README.md) | [简体中文](README.zh-cn.md)

> 这是一款使用 JavaFX 为 *[G52SWM CW2](https://projects.cs.nott.ac.uk/psysz4/swm)* 编写的吃豆人游戏。项目中使用了一些设计模式，如 MVC、单例模式（Singleton）、工厂模式（Factory）等。

## 目录

- [截图](#截图)
- [环境要求](#环境要求)
- [编译运行](#编译运行)
- [源文件结构](#源文件结构)
- [编写新关卡](#编写新关卡)
- [文件存储](#文件存储)
- [设计模式](#设计模式)
- [关于 G52SWM CW2](#关于-g52swm-cw2)
- [版权](#版权)

## 截图

![screenshots.jpg](https://i.loli.net/2018/12/29/5c266b2ff35e1.jpg)

## 环境要求

- Java 10

> 同时兼容:
>
> - Java 11
> - Maven
>
> *(因为 [JavaFX 11](https://openjfx.io/index.html) 从 Java 11 中被移除, 如果你使用的是 Java 11，那么你需要安装 [Maven](https://maven.apache.org/) 来获取依赖。)*

## 编译运行

你可以通过在 IDE 中运行 `pacman.Main.main()` 方法来启动程序。或者如果安装了maven，可以在命令行上键入 `mvn clean compile package exec：java`。

比如，在 IntelliJ 中，点击 <kbd>Run</kbd>  -> <kbd>Run 'Main()'</kbd>.

## 源文件结构

```
src
└── main
    ├── java
    |   └── pacman
    |       ├── constant
    |       ├── controller
    |       ├── model
    |       ├── util
    |       └── Main.java
    └── resources
        └── pacman
            ├── css
            ├── image
            ├── map
            ├── music
            └── view
```

## 编写新关卡

通过在 `/ src / main / resources / pacman / map` 下创建一个新的 ASCII `.txt` 文件，可以轻松添加一个新关卡。

### 步骤一：创建 ASCII 文件

这是一个示例文件 `example.txt`，用于定义关卡的初始状态：

```
/* 这是注释行 */
@PACMAN_PADDING_RATE 0.1
@PACMAN_STEP_RATE 0.15
@GHOST_PADDING_RATE 0.15
@GHOST_STEP_RATE 0.18
@COOKIE_PADDING_RATE 0.5

#########################
#@....#...........#....o#
#.###.#.#########.#.###.#
#.#O.................O#.#
#.#.###.###   ###.###.#.#
#.#.....#     X #.....#.#
<...###.#       #.###...>
#.#.....# X X X #.....#.#
#.#.###.#########.###.#.#
#.#O.................O#.#
#.###.#.#########.#.###.#
#o....#...........#....o#
#########################
```

#### 符号

|   符号    |           模型            |
| :-------: | :-----------------------: |
|    `#`    |    障碍（obstacle）。     |
|    `@`    |    吃豆人（pacman）。     |
|    `.`    | `1` 分的饼干（cookie）。  |
|    `o`    | `5` 分的饼干（cookie）。  |
|    `O`    | `10` 分的饼干（cookie）。 |
|    `X`    |      幽灵（ghost）。      |
| `<` & `>` |    传送门（portals）。    |
|    ` `    | 开放空间（open space）。  |

> 注意，当定义传送门时，二者缺一不可。

#### 配置

|           键           |                值                |
| :--------------------: | :------------------------------: |
| `@PACMAN_PADDING_RATE` | 吃豆人的边距比率。默认值：`0.1`  |
|  `@PACMAN_STEP_RATE`   | 吃豆人的步进比率。默认值：`0.1` |
|  `@GHOST_PADDING_RATE` | 幽灵们的边距比率。默认值：`0.2` |
|   `@GHOST_STEP_RATE`   | 幽灵们的步进比率。默认值：`0.1` |
| `@COOKIE_PADDING_RATE` |  饼干的边距比率。默认值：`0.3`   |

> 边距定义了当两个模型接触时的可接受重叠区域。
>
> 步进定义了一个可移动模型的移动速度。

### 步骤二：将文件名添加到常量

在  `src/main/java/pacman/constant/FileName.java` 中，把我们在步骤一中的地图文件名添加到 `MAPS` 中：

```java
  public static final Set<String> MAPS =
      new TreeSet<>(
          Arrays.asList(
              "pacman/map/#001 So Easy.txt",
              "pacman/map/#002 Easy Again?.txt",
              "pacman/map/#003 A Traitor.txt",
              "pacman/map/#004 Freedom.txt",
              "pacman/map/#005 Less is More.txt",
              "pacman/map/#006 Up and Down.txt",
              "pacman/map/#007 One Way.txt",
              "pacman/map/#008 The Maze.txt",
              "pacman/map/#009 Accel World.txt",
              "pacman/map/#010 Spires.txt",
              "pacman/map/example.txt"));
```

完成了！一个新关卡创建了。

## 文件存储

该项目将记分板（scoreboards）存储在用户的主目录中： `~/.pacman`。

当你不想再与这个游戏纠缠时，可以随意删除它。

## 设计模型

- MVC
- 单例模式（Singleton）
- 工厂模式（Factory）
- 建造者模式（Builder）

## 关于 G52SWM CW2

点击[此处](https://projects.cs.nott.ac.uk/psysz4/swm)了解有关其要求的更多信息。

我最后提交的课程作品被标记为 `1.0` 的标签。

### 类图

![](https://ws4.sinaimg.cn/large/006tNbRwgy1fyn2czxz5qj314y0u0k1y.jpg)

### 演示视频

https://youtu.be/U7muRCK_orU

## 版权

该项目修改自一个[基础版本](https://projects.cs.nott.ac.uk/psysz4/swm)。

所有媒体资源（图像和音乐）均来自互联网，并确保可以免费用于非商业用途。

该项目采用 MIT 许可证。