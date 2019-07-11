### 背景
按钮应该是我们的App里面最普遍的组件之一了，特别常用。    
通常我们写一个按钮的套路也很固定很简单。大概分为以下几个步骤：

1. 在xml布局里面按照设计稿里面的尺寸位置写一个Textview
2. 按照设计稿规定的颜色和圆角在drawable目录下创建一个shape文件
3. 将这个shape文件作为Textview的背景

这样一个很标准的按钮就诞生了，然后就可以继续愉快的开发了。这本来没有什么问题，也比让UI妹纸切图高级了很多，但是随着开发的进行你会发现，UI妹纸的想法很多，不同的界面有各种不同圆角和不同背景颜色的按钮，这个时候你需要把上面的三个步骤再进行N次。

最后你会发现你的drawable目录下有各种各样的按钮背景资源，难以管理。特别假如有的按钮要求有点击效果时可以使用selector，这个时候可能就会产生三个文件用来满足要求，所以总得来说很繁琐。

### 想法
基于以上原因，以及按钮使用到的普遍程度，感觉很有必要写一个使用简单且能满足日常各种需求的按钮。我们先梳理下按钮需要达到的效果：

1. 使用简单(即可以利用属性对按钮进行各种设置)
2. 可以支持设置按钮文字、按钮文字颜色、按钮文字大小
3. 可以支持统一设置圆角大小，也可以单独设置按钮每个圆角的大小
4. 可以支持设置按钮背景颜色
5. 可以支持selector
6. 可以支持圆形按钮
7. 可以支持渐变色按钮，可以支持各个方向设置渐变色
8. 可以支持设置带边框的按钮，可以设置边框的颜色和宽度
9. 可以支持设置按钮是否可以点击
10. 可以设置带图标的按钮，支持自定义按钮大小，和自动缩放，图标支持设置在文字上下左右四个方向，支持自定义文字距离图标的距离

### 实现效果

<table>
<tr>
<td align="center" >效果</td><td align="center" >典型代码</td><td align="center" >说明</td>
</tr>

<tr>
<td width="40%">
<img src="https://raw.githubusercontent.com/ansnail/tc/master/20190710220650.png" />
</td>
<td width="35%">

```
  <top.androidman.superbutton.SuperButton
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:layout_margin="20dp"
        app:color_normal="@color/color_accent"
        app:corner="10dp"
        app:text="@string/poetry_1"
        app:textColor="@color/color_white"
        app:textSize="22sp" />
  ```
</td>
<td width="50%">
    按钮文字<br>
    text<br><br>
    按钮文字颜色<br>
    textColor<br><br>
    按钮文字大小<br>
    textSize<br><br>
    按钮背景颜色<br>
    color_normal<br><br>
    按钮四个角的圆角角度<br>
    corner
</td>
</tr>

<tr>
<td width="40%">
<img src="https://raw.githubusercontent.com/ansnail/tc/master/20190710220532.png" />
</td>
<td width="35%">

```
  <top.androidman.superbutton.SuperButton
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:layout_margin="20dp"
        app:color_normal="@color/color_6596ff"
        app:corner="40dp"
        app:corner_left_bottom="0dp"
        app:text="单独设置左下角为0dp"
        app:textColor="@color/color_white"
        app:textSize="22sp" />
  ```
</td>
<td width="50%">
    单独设置左下角角度<br>
    corner_left_bottom<br><br>
    单独设置左上角角度<br>
    corner_left_top<br><br>
    单独设置右上角角度<br>
    corner_right_top<br><br>
    单独设置右下角角度<br>
    corner_right_bottom<br><br>
    <font color="red">注意：单独设置角度会覆盖corner属性</font>
</td>
</tr>


<tr>
<td width="40%">
<img src="https://raw.githubusercontent.com/ansnail/tc/master/image/20190711002844.gif" />
</td>
<td width="35%">

```
  <top.androidman.superbutton.SuperButton
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:layout_margin="20dp"
        android:onClick="test"
        app:color_normal="@color/color_accent"
        app:color_pressed="@color/color_ffB419"
        app:corner="10dp"
        app:text="点击会变色哦"
        app:textColor="@color/color_white"
        app:textSize="22sp" />
  ```
</td>
<td width="50%">
    设置此属性当点击的时候会有相应效果<br>
    color_pressed<br>
    
</td>
</tr>

<tr>
<td width="40%">
<img src="https://raw.githubusercontent.com/ansnail/tc/master/image/20190711020844.png" />
</td>
<td width="35%">

```
  <top.androidman.superbutton.SuperButton
        android:layout_width="160dp"
        android:layout_height="160dp"
        android:layout_margin="20dp"
        android:onClick="test"
        app:color_normal="@color/color_accent"
        app:drawable_middle="@mipmap/icon_like"
        app:drawable_middle_height="50dp"
        app:drawable_middle_width="50dp"
        app:shape="CIRCLE" />
  ```
</td>
<td width="50%">
    设置此属性只有图标没有文字<br>
    drawable_middle<br>
    图片宽<br>
    drawable_middle_width<br>
    图片高<br>
    drawable_middle_height<br>
    按钮形状，默认为RECT(矩形)<br>
    shape<br>
    <font color="red">注意：当设置此属性时，文字的相关属性将会失效</font>
</td>
</tr>

<tr>
<td width="40%">
<img src="https://raw.githubusercontent.com/ansnail/tc/master/image/20190711005025.png" />
</td>
<td width="35%">

```
  <top.androidman.superbutton.SuperButton
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:layout_margin="20dp"
        app:color_direction="RIGHT_LEFT"
        app:color_end="@color/color_14c7de"
        app:color_start="@color/color_red"
        app:corner="20dp"
        app:text="从右到左渐变"
        app:textColor="@color/color_white"
        app:textSize="22sp" />
  ```
</td>
<td width="50%">
    开始颜色<br>
    color_start<br>
    结束颜色<br>
    color_end<br>
    颜色渐变方向<br>
    color_direction<br>
    <font color="red">注意：当设置颜色渐变时，color_normal设置将失效</font>
    
    
</td>
</tr>

<tr>
<td width="40%">
<img src="https://raw.githubusercontent.com/ansnail/tc/master/image/20190711005235.png" />
</td>
<td width="35%">

```
  <top.androidman.superbutton.SuperButton
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:layout_margin="20dp"
        app:border_color="@color/color_red"
        app:border_width="2dp"
        app:color_normal="@color/color_accent"
        app:corner="10dp"
        app:text="@string/poetry_1"
        app:textColor="@color/color_white"
        app:textSize="22sp" />
  ```
</td>
<td width="50%">
    边框颜色<br>
    border_color<br>
    边框宽度<br>
    border_width<br>
    
</td>
</tr>

<tr>
<td width="40%">
<img src="https://raw.githubusercontent.com/ansnail/tc/master/image/20190711005453.png" />
</td>
<td width="35%">

```
  <top.androidman.superbutton.SuperButton
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:layout_margin="20dp"
        android:onClick="test"
        app:button_click_able="false"
        app:color_normal="@color/color_accent"
        app:corner="10dp"
        app:singleLine="false"
        app:text="app:button_click_able=false\n也可以代码设置"
        app:textColor="@color/color_white"
        app:textSize="22sp" />
  ```
</td>
<td width="50%">
    按钮是否可以点击，默认可以点击<br>
    button_click_able<br>
    按钮文字是否单行，默认是单行<br>
    singleLine<br>
    <font color="red">注意：按钮提供有方法对点击事件进行控制，请使用相关方法</font>
</td>
</tr>

<tr>
<td width="40%">
<img src="https://raw.githubusercontent.com/ansnail/tc/master/image/20190711005743.png" />
</td>
<td width="35%">

```
  <top.androidman.superbutton.SuperButton
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:layout_margin="20dp"
        app:color_normal="@color/color_red"
        app:corner="20dp"
        app:drawable_padding="20dp"
        app:drawable_right="@mipmap/icon_like"
        app:text="图标在右边"
        app:textColor="@color/color_white"
        app:textSize="22sp" />
  ```
</td>
<td width="50%">
    图标在文字右边<br>
    drawable_right<br><br>
    图标在文字左边<br>
    drawable_left<br><br>
    图标在文字上边<br>
    drawable_top<br><br>
    图标在文字下边<br>
    drawable_bottom<br><br>
    图标距文字距离<br>
    drawable_padding<br><br>
    根据文字大小缩放图标，默认为true<br>
    drawable_auto<br><br>
    
</td>
</tr>

</table>

### 按钮支持的所有属性
```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="SuperButton">
        <!--默认配置-->
        <attr name="text" format="reference|string" />
        <!--按钮文字颜色-->
        <attr name="textColor" format="reference|color" />
        <!--按钮文字大小-->
        <attr name="textSize" format="dimension" />
        <!--文字是否单行，默认单行-->
        <attr name="singleLine" format="boolean" />

        <!--默认背景颜色-->
        <attr name="color_normal" format="reference|color" />
        <!--按压时的背景颜色-->
        <attr name="color_pressed" format="reference|color" />

        <!--图片在文字左边-->
        <attr name="drawable_left" format="reference|color" />
        <!--图片在文字右边-->
        <attr name="drawable_right" format="reference|color" />
        <!--图片在文字上边-->
        <attr name="drawable_top" format="reference|color" />
        <!--图片在文字下边-->
        <attr name="drawable_bottom" format="reference|color" />
        <!--图片距文字的距离-->
        <attr name="drawable_padding" format="dimension" />
        <!--图标根据文字大小自动缩放图标-->
        <attr name="drawable_auto" format="boolean" />

        <!--只有图片的情况,此时会忽略文字，即便设置-->
        <attr name="drawable_middle" format="reference|color" />
        <!--图片在中间时宽-->
        <attr name="drawable_middle_width" format="dimension" />
        <!--图片在中间时高-->
        <attr name="drawable_middle_height" format="dimension" />

        <!--形状-->
        <attr name="shape" format="enum">
            <!--圆形-->
            <enum name="CIRCLE" value="1" />
            <!--矩形-->
            <enum name="RECT" value="2" />
        </attr>

        <!--按钮背景是渐变色时设置-->
        <!--开始颜色-->
        <attr name="color_start" format="color" />
        <!--结束颜色-->
        <attr name="color_end" format="color" />
        <!--颜色方向-->
        <attr name="color_direction" format="enum">
            <!--draw the gradient from the top to the bottom-->
            <enum name="TOP_BOTTOM" value="0x1" />
            <!--draw the gradient from the top-right to the bottom-left-->
            <enum name="TR_BL" value="0x2" />
            <!--draw the gradient from the right to the left-->
            <enum name="RIGHT_LEFT" value="0x3" />
            <!--draw the gradient from the bottom-right to the top-left-->
            <enum name="BR_TL" value="0x4" />
            <!--draw the gradient from the bottom to the top-->
            <enum name="BOTTOM_TOP" value="0x5" />
            <!--draw the gradient from the bottom-left to the top-right-->
            <enum name="BL_TR" value="0x6" />
            <!--draw the gradient from the left to the right-->
            <enum name="LEFT_RIGHT" value="0x7" />
            <!--draw the gradient from the top-left to the bottom-right-->
            <enum name="TL_BR" value="0x8" />
        </attr>

        <!--按钮圆角，如果单独设置会覆盖此设置-->
        <attr name="corner" format="dimension" />
        <!--左上角圆角半径-->
        <attr name="corner_left_top" format="dimension" />
        <!--左下角圆角半径-->
        <attr name="corner_left_bottom" format="dimension" />
        <!--右上角圆角半径-->
        <attr name="corner_right_top" format="dimension" />
        <!--右下角圆角半径-->
        <attr name="corner_right_bottom" format="dimension" />

        <!--边框宽度-->
        <attr name="border_width" format="dimension" />
        <!--边框颜色-->
        <attr name="border_color" format="color" />
        <!--按钮是否可以点击-->
        <attr name="button_click_able" format="boolean" />

    </declare-styleable>
</resources>
```

### 高级应用
1.想修改按钮相关调用如下方法：
```
    /**
     * 修改文字
     */
    superButton.setText(CharSequence text);

    /**
     * 修改文字颜色
     */
    superButton.setTextColor(@ColorInt int textColor);

    /**
     * 修改按钮背景颜色
     */
    superButton.setColorNormal(@ColorInt int colorNormal);

```

当某些状态下需要代码控制，将按钮置灰然后不可点击，可以直接调用如下方法：
```
    /**
     * 调用此方法后按钮颜色被改变，按钮无法点击
     */
    superButton.setUnableColor(@ColorInt int color);
```
如果只是想设置按钮不可点击，不需要改变按钮颜色，可以使用如下方法
```
    /**
     * 设置按钮是否可以点击
     */
    superButton.setButtonClickable(boolean buttonClickable);
```

### 未来展望

未来准备把按钮阴影加入进来，这样这个按钮的使用就趋近于完美啦。    
如果大家有好的想法和需求，欢迎大家提issue。